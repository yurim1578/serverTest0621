package com.project.metasu.member.controller;

import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.repository.ItemRepository;
import com.project.metasu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class CategoryController {
  @Autowired
  private ItemRepository itemRepository;

/*
  //정수기 카테고리
  @GetMapping("/category")
  public String Category(Model model) { return "member/category"; }
*/


//////카테고리에 상품전체출력///db연결실패한컨트롤러...---
  @GetMapping("/category")
  public String showCategoryItems(Model model) {
    List<ItemMaster> items = itemRepository.findAll();
    model.addAttribute("item", items);
    return "member/category";
  }
//db연결실패한거...---////

  }




