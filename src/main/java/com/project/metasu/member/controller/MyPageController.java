package com.project.metasu.member.controller;

import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.repository.ItemMasterRepository;
import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.dto.MemberDto;
import com.project.metasu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("member/mypage")
public class MyPageController {

  private final MemberService memberService;
  private final ItemMasterRepository itemMasterRepository;

  // 마이페이지 메인 화면
//  @GetMapping("")
//  public String myPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
//    // 유저의 ID를 가져옴
//    String userId = userDetails.getUsername();
//    System.out.println(userId);
//
//    // 유저 정보를 가져옴
//    MemberDto memberDto = memberService.findMemberById(userId);
//
//    // 사용자 정보를 엔티티로 변환
//    Member member = memberDto.toEntity();
//
//    // 사용자가 소유한 모든 상품을 가져옴
//    // 20230618
//    // List<ItemMaster> items = itemMasterRepository.findByMember(member);
//
//    model.addAttribute("member", memberDto);
//
//    // 첫 번째 항목이 존재하는 경우에만 출력
//    // 20230618
//    /*if (!items.isEmpty() && items.get(0) != null) {
//      model.addAttribute("item", items.get(0));
//    }*/
//
//    return "member/mypage";
//  }

  @GetMapping("")
  public String myPage(Model model, Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String memberId = userDetails.getUsername();
    Member member = memberService.getInfo(memberId);
    model.addAttribute("member", member);

    return "member/mypage";
  }
//  @GetMapping("/myitems")
//  public String myItems(Model model, @AuthenticationPrincipal UserDetails userDetails) {
//    // 유저의 ID를 가져옴
//    String userId = userDetails.getUsername();
//
//    // 유저 정보를 가져옴
//    MemberDto memberDto = memberService.findMemberById(userId);
//
//    // 사용자 정보를 엔티티로 변환
//    Member member = memberDto.toEntity();
//
//    // 사용자가 소유한 모든 상품을 가져옴
//    List<ItemMaster> items = itemMasterRepository.findByMember(member);
//
//    // 유저 정보와 상품 리스트를 모델에 추가
//    model.addAttribute("user", memberDto);
//    model.addAttribute("items", items);
//
//    return "mypage/myitems";   //'/resources/templates/mypage/myitems.html'
//  }
}

 /* @GetMapping("")
  public String myPage(Model model, Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String memberId = userDetails.getUsername();
    System.out.println(memberId);

    // 사용자 정보 가져오기
    Member member = memberService.getInfo(memberId);
    model.addAttribute("member", member);

    return "member/mypage";
  }

  // 사용중인 제품 페이지
  @GetMapping("/myitems")
  public String myItems(Model model, @AuthenticationPrincipal UserDetails userDetails) {
    // 유저의 ID를 가져옴
    String userId = userDetails.getUsername();

    // 유저 정보를 가져옴
    MemberDto memberDto = memberService.findMemberById(userId);

    // 사용자 정보를 엔티티로 변환
    Member member = memberDto.toEntity();

    // 사용자가 소유한 모든 상품을 가져옴
    List<ItemMaster> items = itemMasterRepository.findByMember(member);

    model.addAttribute("member", memberDto);

    // 첫 번째 항목이 존재하는 경우에만 출력
    if (!items.isEmpty() && items.get(0) != null) {
      model.addAttribute("item", items.get(0));
    }

    return "member/mypage";
  }
}*/


/*
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
  */





