package com.project.metasu.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//  http://localhost:8090/member/register
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

  //회원가입
  @GetMapping("/register")
  public String Register(Model model) {
    return "/member/register";
  }
  //로그인
  @GetMapping("/login")
  public String Login(Model model) {
    return "/member/login";
  }
}

