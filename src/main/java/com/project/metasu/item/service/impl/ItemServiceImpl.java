package com.project.metasu.item.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.metasu.item.domain.entity.*;
import com.project.metasu.item.dto.in.AutoPaymentReq;
import com.project.metasu.item.dto.in.CartReq;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    // 한 상품 이미지
    @Override
    @Transactional
    public ResponseEntity findItemImg(String itemCode) {
        List<ItemImg> itemImgs = itemImgRepository.findTop1ByItemCodeOrderByItemColorCodeDesc(itemCode);
        ItemImgRes itemImgDtos = (ItemImgRes) itemImgs.stream()
                .map(itemImg -> new ItemImgRes(itemImg.getItemImg1(), itemImg.getItemImg2(), itemImg.getItemImg3())).findAny().get();

        return ResponseEntity.ok(itemImgDtos);
    }

    // 한 상품에 대한 컬러코드별 이미지
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

    // user 장바구니 데이터
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

    // user 정보
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

    // user에 대한 장바구니 모든 데이터
    @Override
    public ResponseEntity findAllByMemberId(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("member not found"));
        List<Map<String,Object>> list = cartRepository.findAllByMemberId(memberId);
        return ResponseEntity.ok(list);
    }

    // 렌탈시 주문목록에서 보여줘야할 추가 정보들
    @Override
    public  Map<String,Object> findByRentalOrderInfo(String itemCode, String itemColorCode) {
        Map<String,Object> data = itemMasterRepository.findByRentalOrderInfo(itemCode,itemColorCode);
        return data;
    }

    // 선택한 상품에 대한 모든 리뷰
    @Override
    @Transactional
    public ResponseEntity findByItemCode(String itemCode, String sortValue, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 2, Sort.by(Sort.Direction.DESC, sortValue));
         Page<Review> reviewDto = reviewRepository.findByItemCodeItemCode(itemCode, pageable);
        return ResponseEntity.ok(reviewDto);
    }

    // 설치 날짜 마감되었는지 체크
    @Override
    @Transactional
    public Boolean chkInstallDate(LocalDate installDate, String installTimeCode) {
        Boolean result = deliveryRepository.existsByInstallDateAndInstallTimeCode(installDate, installTimeCode);
        return result;
    }

    // 구매로 주문 (직접 결제)
    @Override
    @Transactional
    public ResponseEntity addPayment(PaymentReq req) {
        Rental rental = req.getRentalReq() != null ? rentalRepository.save(req.getRentalReq().toEntity()) : null;

        OrderMaster orderMaster = orderMasterRepository.save(OrderMaster.builder()
                .orderNo("O_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .member(req.getMemberReq())
                .contract(contractRepository.save(req.getContractReq().toEntity()))
                .delivery(deliveryRepository.save(req.getDeliveryReq().toEntity()))
                .rental(rental)
                .payment(paymentRepository.save(req.getPayReq().toEntity()))
                .orderDiscountYn(req.getOrderMasterReq().getOrderDiscountYn())
                .orderAmount(req.getOrderMasterReq().getOrderAmount())
                .orderStatus(req.getOrderMasterReq().getOrderStatus())
                .build());

            List<ItemStock> itemStockList = itemStockRepository.findByItemBarcode(req.getMemberReq().getMemberId());
            List<OrderDetail> orderDetails = itemStockList.stream()
                    .map(itemStock -> OrderDetail.builder()
                            .orderMaster(orderMaster)
                            .itemStock(itemStock)
                            .build())
                    .collect(Collectors.toList());
            List<OrderDetail> savedOrderDetails = orderDetailRepository.saveAll(orderDetails);
            cartRepository.deleteByMemberId(req.getMemberReq().getMemberId()); //카트 삭제

        return ResponseEntity.ok(HttpStatus.OK.value());
    }

    // 렌탈 정기결제 (빌링키 생성 + 주문 save)
    @Override
    @Transactional
    public ResponseEntity autoPayment(String authKey,String customerKey,String str) throws IOException, InterruptedException {

            String requestBody = "{\"authKey\":\"" + authKey + "\",\"customerKey\":\"" + customerKey + "\"}"; // 권한키와 커스텀키 파싱

            // 토스 빌링키 발급 api
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/issue"))
                    .header("Authorization", "Basic dGVzdF9za181R2VQV3Z5Sm5yS2Vhbndsd3ZxM2dMek45N0VvOg==")
                    .header("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body(); // 토스에서 넘어온 json 데이터 string으로 파싱
            String pramJson = str; // 결제 페이지에서 넘어온 json 데이터 string으로 파싱

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 원하는 데이터만 저장되게
            PaymentReq paymentReq = objectMapper.readValue(pramJson, PaymentReq.class); // paymentReq에 파싱한 json 데이터 저장
            AutoPaymentReq autoPaymentReq = objectMapper.readValue(json, AutoPaymentReq.class); // autoPaymentReq에 파싱한 json 데이터 저장

        // 렌탈 save
            Rental rental = rentalRepository.save(AutoPaymentReq.builder()
                            .billingKey(autoPaymentReq.getBillingKey())
                            .customerKey(autoPaymentReq.getCustomerKey())
                            .rentalStartDate(paymentReq.getRentalReq().getRentalStartDate())
                            .rentalEndDate(paymentReq.getRentalReq().getRentalEndDate())
                            .rentalPeriod(paymentReq.getRentalReq().getRentalPeriod())
                            .rentalPayAutoYn(autoPaymentReq.getRentalPayAutoYn())
                            .rentalPayAutoDate(paymentReq.getRentalReq().getRentalPayAutoDate())
                            .rentalStatus(paymentReq.getRentalReq().getRentalStatus())
                            .rentalNo(paymentReq.getRentalReq().getRentalNo())
                            .build().toEntity());
            // 결제 save
            Payment payment = paymentRepository.save(Payment.builder()
                    .paymentNo(autoPaymentReq.getPaymentNo())
                    .paymentType(autoPaymentReq.getMethod())
                    .paymentCreditNumber(autoPaymentReq.getCardNumber())
                    .paymentAccount(paymentReq.getPayReq().getPaymentAccount())
                    .paymentBank(autoPaymentReq.getCardCompany())
                    .paymentAmount(paymentReq.getPayReq().getPaymentAmount())
                    .paymentStatus(autoPaymentReq.getPaymentStatus())
                    .rental(rental)
                    .build());

            // 주문 마스터 save
            OrderMaster orderMaster = orderMasterRepository.save(OrderMaster.builder()
                    .orderNo("O_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                    .member(paymentReq.getMemberReq())
                    .contract(contractRepository.save(paymentReq.getContractReq().toEntity())) // 계약 save
                    .delivery(deliveryRepository.save(paymentReq.getDeliveryReq().toEntity())) // 설치(배달) sve
                    .rental(rental)
                    .payment(payment)
                    .orderDiscountYn(paymentReq.getOrderMasterReq().getOrderDiscountYn())
                    .orderAmount(paymentReq.getOrderMasterReq().getOrderAmount())
                    .orderStatus(paymentReq.getOrderMasterReq().getOrderStatus())
                    .build());
            // 주문 디테일 save
            OrderDetail orderDetail = orderDetailRepository.save(OrderDetail.builder()
                    .orderMaster(orderMaster)
                    //itemStock에서 바코드 find 후 build, 판매여부 Y로 update
                    .itemStock(itemStockRepository.findTop1ByItemCodeAndItemColorCodeAndSalesYn(paymentReq.getItemStockReq().getItemCode(), paymentReq.getItemStockReq().getItemColorCode(), false))
                    .rental(rental)
                    .build());

            return ResponseEntity.ok(rental.getRentalNo());

    }

    // 배치 돌아갈때 결제 승인 + 결제 save
    @Override
    @Transactional
    public ResponseEntity acceptPayment(String rentalNo)  throws IOException, InterruptedException {
        Rental rental = rentalRepository.findById(rentalNo).orElseThrow(() -> new IllegalArgumentException("rental not found"));
        OrderMaster orderMaster =orderMasterRepository.findByRentalNo(rental.getRentalNo());

        // 토스 결제승인 api
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/billing/" + rental.getBillingKey()))
                .header("Authorization", "Basic dGVzdF9za181R2VQV3Z5Sm5yS2Vhbndsd3ZxM2dMek45N0VvOg==")
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"customerKey\":\"" + rental.getCustomerKey() + "\",\"amount\":" + (orderMaster.getOrderAmount()/Integer.parseInt(rental.getRentalPeriod())) + ",\"orderId\":\"" + orderMaster.getOrderNo() + "\",\"orderName\":\"렌탈 자동결제\"}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // 결제테이블에서 가장 최근 렌탈번호로 정보 find (결제방식 변화 불가하기때문에)
        Payment newPayment = paymentRepository.findTop1ByRentalNoOrderByCreatedDate(rentalNo);
        // paymentNo 생성해서 결제 승인떨어졌을시 insert todo: 원래 response status로 예외처리 해주어야함
        paymentRepository.save(Payment.builder()
                        .paymentNo("P_" + LocalDateTime.now())
                .paymentType(newPayment.getPaymentType())
                .paymentCreditNumber(newPayment.getPaymentCreditNumber())
                .paymentAccount(newPayment.getPaymentAccount())
                .paymentBank(newPayment.getPaymentBank())
                .paymentAmount(newPayment.getPaymentAmount())
                .paymentStatus(newPayment.getPaymentStatus())
                .rental(rental)
                .build());

        log.info("결제 승인이 완료되었습니다. " + response.body());
        return ResponseEntity.ok(HttpStatus.OK.value());
    }

    // 결제실패 or 취소시
    @Override
    public ResponseEntity notAcceptPayment(String rentalNo) throws IOException, InterruptedException {

        Rental rental = rentalRepository.findById(rentalNo).orElseThrow(() -> new IllegalArgumentException("rental not found"));
        rentalRepository.save(Rental.builder()
                .billingKey(rental.getBillingKey())
                .customerKey(rental.getCustomerKey())
                .rentalStartDate(rental.getRentalStartDate())
                .rentalEndDate(rental.getRentalEndDate())
                .rentalPeriod(rental.getRentalPeriod())
                .rentalPayAutoYn(false) // 자동결제 false로 update
                .rentalPayAutoDate(rental.getRentalPayAutoDate())
                .rentalStatus("PF") // 상태코드 결제실패로 update
                .rentalNo(rental.getRentalNo())
                .build());

        return ResponseEntity.ok(HttpStatus.OK.value());
    }

}
