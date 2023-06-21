package com.project.metasu.item.controller;

import com.project.metasu.item.dto.in.PaymentReq;
import com.project.metasu.item.dto.in.RentalReq;
import com.project.metasu.item.dto.out.RentalRes;
import com.project.metasu.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/test")
    public String view(Model model) {
        return "/item/itemTest";
    }

    //상품 상세페이지
    @GetMapping("/info/{itemCode}")
    public String list(@PathVariable("itemCode") String itemCode, Model model) {
        ResponseEntity itemMasterDto = itemService.findItem(itemCode);              // itemMaster 정보
        ResponseEntity itemDetailDto = itemService.findItemColorCode(itemCode);     // itemCode의 itemColorCode
        ResponseEntity itemImgDto = itemService.findItemImg(itemCode);              // 컬러별 상품 이미지

        model.addAttribute("post", itemMasterDto.getBody());
        model.addAttribute("detail", itemDetailDto.getBody());
        model.addAttribute("img", itemImgDto.getBody());
        return "/item/itemDetail";
    }

    // 장바구니
    @GetMapping("/cart/{memberId}")
    public String cart(@PathVariable("memberId") String memberId, Model model) {
        ResponseEntity cartDto = itemService.findCart(memberId);    // memberId의 장바구니
        model.addAttribute("dto2", cartDto.getBody());
        return "/item/shop-cart";
    }

    // 장바구니에서 결제페이지 이동
    @GetMapping("/salesOrder/{memberId}")
    public String salesOrder(@PathVariable("memberId") String memberId, Model model) {
        ResponseEntity memberDto = itemService.findByMember(memberId);      // member 정보
        ResponseEntity cartDto = itemService.findAllByMemberId(memberId);   // 장바구니 정보
        model.addAttribute("member", memberDto.getBody());
        model.addAttribute("cart", cartDto.getBody());
        return "/item/checkout";
    }

    // 주문쪽 insert
    @PostMapping("/addPayment")
    public String addPayment(@RequestBody PaymentReq req) {
        itemService.addPayment(req);
        return "redirect:/item/info/1"; // todo: 합치기전이라 상품 상세페이지로 가게 고정해놓음
    }

    // 렌탈 선택시 결제페이지 이동
    @PostMapping("/rentalOrder/{memberId}")
    public String rentalOrder(@PathVariable("memberId") String memberId,RentalReq req, Model model) {
        Map<String,Object> itemDto = itemService.findByRentalOrderInfo(req.getItemCode(), req.getItemColorCode());
        ResponseEntity memberDto = itemService.findByMember(memberId);      // member 정보
        RentalRes rentalRes = RentalRes.builder()
                .itemCode(req.getItemCode())
                .itemColorCode(req.getItemColorCode())
                .memberId(req.getMemberId())
                .rentalPeriod(req.getRentalPeriod())
                .rentalPayAutoDate(req.getRentalPayAutoDate())
                .itemName(String.valueOf(itemDto.get("item_name")))
                .itemColorCodeName(String.valueOf(itemDto.get("item_color_code_name")))
                .itemPrice((Integer) itemDto.get("item_price"))
                .build();
        model.addAttribute("member", memberDto.getBody());
        model.addAttribute("rentalDto", rentalRes);
        return "/item/checkout";
    }

    // 특정상품에 대한 리뷰
    @PostMapping("/reviewOfItemCode")
    public String findReviewByItemCode(String itemCode,String sortValue, Model model,
                                       @PageableDefault(page = 0) Pageable pageable) {
       ResponseEntity reviewDto = itemService.findByItemCode(itemCode, sortValue, pageable);
        model.addAttribute("reviewDto", reviewDto.getBody());
        return "/item/review";
    }

    // 렌탈로 주문시 정기결제 빌링키 생성 + 주문 트랜잭션 insert
    @GetMapping("/billingSuccess")
    public String billingSuccess(@RequestParam String authKey, @RequestParam String customerKey, @RequestParam("str") String str) throws IOException, InterruptedException {
        itemService.autoPayment(authKey,customerKey,str);
        return "redirect:/item/info/1"; // todo: 합치기전이라 상품 상세페이지로 가게 고정해놓음
    }
/*

    // 배치돌아갈때 결제승인 요청 + 응답 + payment insert
    @GetMapping("/acceptPayment/{rentalNo}")
    public String acceptPayment(@PathVariable("rentalNo") String rentalNo) throws IOException, InterruptedException {
        itemService.acceptPayment(rentalNo);
        return "/item/itemTest";
    }*/
}
