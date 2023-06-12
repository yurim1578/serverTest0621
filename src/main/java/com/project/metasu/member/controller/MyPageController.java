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
public class MyPageController {
  @Autowired
  private MemberRepository memberRepository;

  //my-page
  @GetMapping("/my-page")
  public String MyPage(Model model) { return "member/my-page"; }
}

