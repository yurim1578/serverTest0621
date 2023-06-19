package com.project.metasu.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/sdfsdfsdfs")
public class MemberController {
  // 로그인 실패 시 로그인 페이지로 이동
  @GetMapping("/login")
  public String Login() {
    return "member/login";
  }

}
