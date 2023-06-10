package com.project.metasu.member.controller;

import com.project.metasu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  @Autowired
  private MemberRepository memberRepository;

  //회원가입
  //로그인
  @GetMapping("/login")
  public String Login(Model model) {
    return "/member/login";
  }


  //정수기 카테고리
  @GetMapping("/category")
  public String Category(Model model) { return "/member/category"; }
  //my-page
  @GetMapping("/my-page")
  public String MyPage(Model model) { return "/member/my-page"; }
}

