package com.project.metasu.member.controller;

import com.project.metasu.item.domain.entity.OrderMaster;
import com.project.metasu.item.domain.entity.Review;
import com.project.metasu.item.dto.ReviewDTO;
import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.service.MemberService;
import com.project.metasu.member.service.OrderMasterService;
import com.project.metasu.member.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private OrderMasterService orderMasterService;

  @Autowired
  private ReviewService reviewService;

  @GetMapping("/write")
  public String writeReview(Model model) {
    Member member = memberService.getCurrentUser();
    List<OrderMaster> orders = orderMasterService.getOrdersByMember(member);

    if (orders.isEmpty()) {
      model.addAttribute("error", "No orders found for the current user");
      return "error";
    }

    model.addAttribute("order", orders.get(0)); // Select the order you want
    model.addAttribute("review", new ReviewDTO()); // Add an empty review object to fill in the form
    return "review/write";
  }

  @PostMapping("/save")
  public String saveReview(@ModelAttribute Review review) {
    reviewService.saveReview(new ReviewDTO());
    return "redirect:/review/success";
  }


}
