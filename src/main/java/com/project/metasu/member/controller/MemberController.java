package com.project.metasu.member.controller;

import com.project.metasu.item.domain.entity.Review;
import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.dto.MemberDto;
import com.project.metasu.member.service.MemberService;
import com.project.metasu.member.service.OrderMasterService;
import com.project.metasu.member.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  @Autowired
  private final ReviewService reviewService;
  @Autowired
  private final MemberService memberService;
  @Autowired
  private final OrderMasterService orderMasterService;

  @Autowired
  private UserDetailsService userDetailsService;

/*  @Autowired
  public MemberController(ReviewService reviewService, MemberService memberService, OrderMasterService orderMasterService) {
    this.reviewService = reviewService;
    this.memberService = memberService;
    this.orderMasterService = orderMasterService;
  }*/

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    if (userDetails == null || !memberService.matchesPassword(password, userDetails.getPassword())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    return ResponseEntity.ok("Logged in successfully");
  }
  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody MemberDto memberDto) {
    memberService.insert(memberDto);
    return ResponseEntity.ok("Registered successfully");
  }

  @PostMapping("/review")
  public ResponseEntity<Review> writeReview(@RequestBody com.project.metasu.item.dto.ReviewDTO reviewDTO) {
    Member member = memberService.getCurrentUser();
    if (orderMasterService.getOrdersByMember(member).isEmpty()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    Review review = reviewService.saveReview(reviewDTO);
    return ResponseEntity.ok(review);
  }
}
