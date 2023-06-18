package com.project.metasu.member.controller;

import com.project.metasu.member.repository.AdminMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  @Autowired
  private AdminMemberRepository adminMemberRepository;

  //회원가입
  @PostMapping("/resgister")
  public String Resgister(Model model) {
    return "member/login";
  }
  //로그인
  @GetMapping("/login")
  public String Login(Model model) {
    return "member/login";
  }
}

