package com.project.metasu.item.controller;

import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/main")
    public String view(Model model) {
        return "/item/itemDetail";
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
    @GetMapping("/payment/{memberId}")
    public String payment(@PathVariable("memberId") String memberId, Model model) {
        ResponseEntity memberDto = itemService.findByMember(memberId);      // member 정보
        ResponseEntity cartDto = itemService.findAllByMemberId(memberId);   // 장바구니 정보
        model.addAttribute("member", memberDto.getBody());
        model.addAttribute("cart", cartDto.getBody());
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

}
