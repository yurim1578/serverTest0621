package com.project.metasu.item.service.impl;

import com.project.metasu.item.domain.entity.*;
import com.project.metasu.item.dto.in.CartReq;
import com.project.metasu.item.dto.in.OrderReq;
import com.project.metasu.item.dto.in.PaymentReq;
import com.project.metasu.item.dto.out.ItemImgRes;
import com.project.metasu.item.dto.out.MemberRes;
import com.project.metasu.item.repository.*;
import com.project.metasu.item.service.ItemService;
import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemMasterRepository itemMasterRepository;
    private final ItemDetailRepository itemDetailRepository;
    private final ItemImgRepository itemImgRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final ReviewRepository reviewRepository;

    private final ContractRepository contractRepository;
    private final DeliveryRepository deliveryRepository;
    private final RentalRepository rentalRepository;
    private final OrderMasterRepository orderMasterRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ItemStockRepository itemStockRepository;
    private final PaymentRepository paymentRepository;


    // 특정 상품 주요 정보(item_master)
    @Override
    @Transactional
    public ResponseEntity findItem(String itemCode) {
        ItemMaster itemMaster = itemMasterRepository.findById(itemCode).get();
        return ResponseEntity.ok(itemMaster);
    }

    // 특정 상품에 대한 전체 색상 정보
    @Override
    @Transactional
    public ResponseEntity findItemColorCode(String itemCode) {
        List<ItemDetail> lists = itemDetailRepository.findAllByItemCode(itemCode);
        List<String> lists2 = lists.stream().map(e -> e.getItemColorCode()).collect(Collectors.toList());
        return ResponseEntity.ok(lists2);
    }

    @Override
    @Transactional
    public ResponseEntity findItemImg(String itemCode) {
        List<ItemImg> itemImgs = itemImgRepository.findTop1ByItemCodeOrderByItemColorCodeDesc(itemCode);
        ItemImgRes itemImgDtos = (ItemImgRes) itemImgs.stream()
                .map(itemImg -> new ItemImgRes(itemImg.getItemImg1(), itemImg.getItemImg2(), itemImg.getItemImg3())).findAny().get();

        return ResponseEntity.ok(itemImgDtos);
    }


    @Override
    @Transactional
    public ResponseEntity findItemImgOfColor(String itemCode, String itemColorCode) {
        ItemImg itemImgs = itemImgRepository.findByItemCodeAndItemColorCode(itemCode,itemColorCode)
                .orElseThrow(() -> new IllegalArgumentException("ItemImg not found"));
        ItemImgRes itemImgDtos = ItemImgRes.builder()
                .itemImg1(itemImgs.getItemImg1())
                .itemImg2(itemImgs.getItemImg2())
                .itemImg3(itemImgs.getItemImg3())
                .build();
        return ResponseEntity.ok(itemImgDtos);
    }

    // 장바구니 데이터가 있는지 확인
    @Override
    @Transactional
    public Boolean chkCart(CartReq req) {
        Boolean result = cartRepository.existsByItemCodeAndItemColorCodeAndMemberId(req.getItemCode(),req.getItemColorCode(),req.getMemberId());
        return result;
    }

    // 장바구니 insert/update
    @Override
    @Transactional
    public ResponseEntity addCart(CartReq req) {
        ItemMaster im = itemMasterRepository.findById(req.getItemCode())
                .orElseThrow(() -> new IllegalArgumentException("ItemMaster not found"));
        ItemDetail id = itemDetailRepository.findByItemCodeAndItemColorCode(req.getItemCode(), req.getItemColorCode())
                .orElseThrow(() -> new IllegalArgumentException("ItemDetail not found"));
        Member m = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Cart cartDto = cartRepository.findByItemCodeAndItemColorCodeAndMemberId(im.getItemCode(),id.getItemColorCode(),m.getMemberId())
                .orElse(Cart.builder().itemMaster(im).itemColorCode(id.getItemColorCode()).member(m).build());
        cartDto.setCartQty(cartDto.getCartQty() + (req.getCartQty() == null ? 0 : req.getCartQty()));
        cartRepository.save(cartDto);
        return ResponseEntity.ok(HttpStatus.OK.value());
    }

    // 특정 고객의 장바구니 데이터
    @Override
    @Transactional
    public ResponseEntity findCart(String memberId) {
        List<Map<String,Object>> cartDto = cartRepository.findCart(memberId);
        return ResponseEntity.ok(cartDto);
    }

    // 장바구니 삭제
    @Override
    @Transactional
    public ResponseEntity deleteCart(String itemCode, String itemColorCode, String memberId) {
        ItemMaster im = itemMasterRepository.findById(itemCode)
                .orElseThrow(() -> new IllegalArgumentException("ItemMaster not found"));
        ItemDetail id = itemDetailRepository.findByItemCodeAndItemColorCode(itemCode,itemColorCode)
                .orElseThrow(() -> new IllegalArgumentException("ItemDetail not found"));
        Member m = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        cartRepository.deleteByItemCodeAndItemColorCodeAndMemberId(im.getItemCode(),id.getItemColorCode(),m.getMemberId());

        return ResponseEntity.ok(HttpStatus.OK.value());
    }

    // 특정 고객 정보
    @Override
    @Transactional
    public ResponseEntity findByMember(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("member not found"));;
        MemberRes res = MemberRes.builder()
                .memberName(member.getMemberName())
                .memberPhone(member.getMemberPhone())
                .memberEmail(member.getMemberEmail())
                .memberAddr1(member.getMemberAddr1())
                .memberAddr2(member.getMemberAddr2())
                .build();
        return ResponseEntity.ok(res);
    }

    @Override
    public ResponseEntity findAllByMemberId(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("member not found"));
        List<Map<String,Object>> list = cartRepository.findAllByMemberId(memberId);
        return ResponseEntity.ok(list);
    }

    // 특정상품에 대한 모든 리뷰
  /*  @Override
    @Transactional
    public ResponseEntity findReviewByItemCode(String itemCode, String sortValue) {
        //Sort sort = Sort.by(Sort.Direction.DESC, sortValue);
        //int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() -1);
        //pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "created_date"));
        *//*List<Map<String,Object>> reviewDto = reviewRepository.findReviewByItemCode(itemCode);
        reviewDto = reviewDto.stream()
                .sorted(Comparator.comparing((Map<String,Object> e) -> String.valueOf(e.get("created_date"))).reversed())
                .collect(Collectors.toList());*//*
        List<Map<String, Object>> reviewDto = reviewRepository.findReviewByItemCode(itemCode);
        return ResponseEntity.ok(reviewDto);
    }
*/
    // 특정상품에 대한 모든 리뷰
    @Override
    @Transactional
    public ResponseEntity findByItemCode(String itemCode, String sortValue, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 2, Sort.by(Sort.Direction.DESC, sortValue));
        Page<Review> reviewDto = reviewRepository.findByItemCodeItemCode(itemCode, pageable);
        return ResponseEntity.ok(reviewDto);
    }

    @Override
    @Transactional
    public Boolean chkInstallDate(LocalDate installDate, String installTimeCode) {
        Boolean result = deliveryRepository.existsByInstallDateAndInstallTimeCode(installDate, installTimeCode);
        return result;
    }

  /*  @Override
    @Transactional
    public ResponseEntity addOrder(OrderReq req) {
        // 아이템 바코드
        List<Map<String,Object>> itemBarcodeDto = itemStockRepository.findByItemBarcode(req.getItemCode(), req.getItemColorCode(),req.getSalesYn(),req.getOrderQty());
        // 고객 아이디
        Member m = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Cart cartDto = cartRepository.findByItemCodeAndItemColorCodeAndMemberId(im.getItemCode(),id.getItemColorCode(),m.getMemberId())
                .orElse(Cart.builder().itemMaster(im).itemColorCode(id.getItemColorCode()).member(m).build());
        cartRepository.save(cartDto);
        return ResponseEntity.ok(HttpStatus.OK.value());
    }*/

    @Override
    @Transactional
    public ResponseEntity addPayment(PaymentReq req) {

        OrderMaster orderMaster = orderMasterRepository.save(OrderMaster.builder()
                .orderNo("O_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .member(req.getMemberReq())
                .contract(contractRepository.save(req.getContractReq().toEntity()))
                .delivery(deliveryRepository.save(req.getDeliveryReq().toEntity()))
                .rental(req.getRentalReq() != null ? rentalRepository.save(req.getRentalReq().toEntity()) : null)
                .payment(paymentRepository.save(req.getPayReq().toEntity()))
                .orderDiscountYn(req.getOrderMasterReq().getOrderDiscountYn())
                .orderAmount(req.getOrderMasterReq().getOrderAmount())
                .orderStatus(req.getOrderMasterReq().getOrderStatus())
                .build());

                /* orderDetailRepository.save(OrderDetail.builder()
                .orderNo(req.getOrderMasterReq().getOrderNo())
                .orderMaster(req.getOrderMasterReq().toEntity())
                .itemBarcode(itemStockRepository.findByItemBarcode(req.getOrderMasterReq().toEntity(),req.))
                .build());
*/
        return ResponseEntity.ok(HttpStatus.OK.value());
    }

}
