package com.project.metasu.member.controller;

import com.project.metasu.item.repository.ItemRepository;
import com.project.metasu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("member/mypage")
public class MyPageController {

  private MemberRepository memberRepository;
  private ItemRepository itemRepository;


  @GetMapping("")
  public String myPage(Model model) {
    return "member/mypage";
  }


  @GetMapping("/products")
  public String showProductsPage() {
    // 사용중인 제품 페이지로 이동하는 로직을 작성합니다.
    return "member/mypage_category";
  }

  @GetMapping("/payment")
  public String showPaymentPage() {
    // 렌탈 자동이체 페이지로 이동하는 로직을 작성합니다.
    return "payment";
  }

  @GetMapping("/payment-method")
  public String showPaymentMethodPage() {
    // 납부방법 변경 페이지로 이동하는 로직을 작성합니다.
    return "payment-method";
  }

  @GetMapping("/wishlist")
  public String showWishlistPage() {
    // 위시리스트 페이지로 이동하는 로직을 작성합니다.
    return "wishlist";
  }

  @GetMapping("/order-history")
  public String showOrderHistoryPage() {
    // 주문내역 페이지로 이동하는 로직을 작성합니다.
    return "order-history";
  }

  @GetMapping("/return-exchange")
  public String showReturnExchangePage() {
    // 반품 교환 내역 페이지로 이동하는 로직을 작성합니다.
    return "return-exchange";
  }

  @GetMapping("/my-reviews")
  public String showMyReviewsPage() {
    // 나의 리뷰 페이지로 이동하는 로직을 작성합니다.
    return "my-reviews";
  }

  @GetMapping("/edit-profile")
  public String showEditProfilePage() {
    // 회원 수정 페이지로 이동하는 로직을 작성합니다.
    return "edit-profile";
  }

  @GetMapping("/delete-account")
  public String showDeleteAccountPage() {
    // 회원 탈퇴 페이지로 이동하는 로직을 작성합니다.
    return "delete-account";
  }
}



