package com.project.metasu.admin.controller;

import com.project.metasu.admin.service.*;

import com.project.metasu.item.domain.dto.AdminItemDto;
import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.member.domain.dto.AdminMemberDto;
import com.project.metasu.member.domain.dto.AdminTestSessionMember;
import com.project.metasu.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//git Test2
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
  String memberAuth="MB";

  private final MemberListService memberService;
  private final OrderService orderService;
  private final ReviewService reviewService;
  private final ItemManageService itemService;
  private final RentalService rentalService;
  //test
  @GetMapping("/gostarter")
  public String view2(Model model) {
    return "/admin/starter";
  }

  //memberList_admin
  @GetMapping("/memberList")
  public String memberList(HttpServletRequest request, Model model){
    HttpSession session = request.getSession(false);
    if(session != null){
      return "redirect:/admin/gostarter";
    }

    //관리자 로그인 세션이 존재
    AdminTestSessionMember findMember = new AdminTestSessionMember("hye","이혜경");
    //원래는 (SessionMember)session.getSession("url")해서 가져와야함

    //System.out.println(findMember);

    if(findMember == null){
      return "redirect:/admin/gostarter";
    }else{  //로그인 됐을떄
      List<Member> member = memberService.findAllMembers(memberAuth);
      int orderNum, reviewNum;
      List<AdminMemberDto> adminMemberDto =new ArrayList<>();

      for(int i=0; i<member.size(); i++){
        orderNum = orderService.findOrderNumById(member.get(i));
        reviewNum = reviewService.findReviewNumById(member.get(i));

        AdminMemberDto tmp=new AdminMemberDto(member.get(i).getMemberId(), member.get(i).getMemberPw(), member.get(i).getMemberName(),
            member.get(i).getMemberAddr1(), member.get(i).getMemberAddr2()
            ,member.get(i).getMemberPhone(), member.get(i).getMemberEmail(),member.get(i).getCreatedDate(),orderNum,reviewNum);
        adminMemberDto.add(tmp);
      }
      //System.out.println(memberDto.toString());

      model.addAttribute("members", adminMemberDto);
      return "admin/memberManagement";
    }
  }

  @GetMapping("/memberDetail/{id}")
  public String getMemberDetail(@PathVariable String id,Model model) {
    //System.out.println(id);
    Member member = memberService.findByMemberId(id);
    int orderNum=orderService.findOrderNumById(member);
    int reviewNum = reviewService.findReviewNumById(member);

    AdminMemberDto adminMemberDto = new AdminMemberDto(member.getMemberId(), member.getMemberPw(), member.getMemberName(),
        member.getMemberAddr1(), member.getMemberAddr2()
        ,member.getMemberPhone(), member.getMemberEmail(),member.getCreatedDate(),orderNum,reviewNum);

    model.addAttribute("member", adminMemberDto);

    return "/admin/memberDetail";
  }

  @GetMapping("/itemManagement")
  public String itemList(HttpServletRequest request, Model model){
    HttpSession session = request.getSession(false);
    if(session != null){
      return "redirect:/admin/gostarter";
    }
    //관리자 로그인 세션이 존재
    AdminTestSessionMember findMember = new AdminTestSessionMember("hye","이혜경");
    //원래는 (SessionMember)session.getSession("url")해서 가져와야함

    //System.out.println(findMember);
    if(findMember == null){
      return "redirect:/admin/gostarter";
    }else{
      model.addAttribute("items",itemService.getItemList());
      return "/admin/itemManagement";
    }
  }

  @GetMapping("/itemDetail/{code}")
  public String getItemDetail(@PathVariable String code, Model model){
    ItemMaster itemDetails = itemService.getItemDetails(code);
    model.addAttribute("item",itemDetails); //
    model.addAttribute("images",itemService.getItemImg(code)); //list
    model.addAttribute("stocks",itemService.getStockList(code));  //map
    model.addAttribute("colors",itemService.getStockDetails(code)); //map

    return "/admin/itemDetail";
  }

  @GetMapping("/orderList")
  public String getOrderList(HttpServletRequest request, Model model){
    HttpSession session = request.getSession(false);
    if(session != null){
      return "redirect:/admin/gostarter";
    }
    //관리자 로그인 세션이 존재
    AdminTestSessionMember findMember = new AdminTestSessionMember("hye","이혜경");
    //원래는 (SessionMember)session.getSession("url")해서 가져와야함

    //System.out.println(findMember);
    if(findMember == null){
      return "redirect:/admin/gostarter";
    }else {
      model.addAttribute("orders",orderService.getOrderLists());
      return "/admin/orderManagement";
    }
  }

  @GetMapping("/orderDetail/{no}")
  public String getOrderDetail(@PathVariable String no, Model model){
    Map<String, Object> orderDetail = orderService.getOrderDetail(no);  //map
    model.addAttribute("order",orderDetail);

    //rental 정보 가져오기-관리
    String rentalNo= (String) orderDetail.get("rental_no");
    model.addAttribute("rental",rentalService.getRentalNPayment(rentalNo));

    //계약 정보->영수증, 계약서 관리
//    String contractNo=orderDetail.get("contract_no").toString();  //map의 contract_no컬럼의 값을 String으로 가져옴
    String contractNo=String.valueOf(orderDetail.get("contract_no"));  //map의 contract_no컬럼의 값을 String으로 가져옴
    model.addAttribute("contract",orderService.getContract(contractNo));
    return "/admin/orderDetail";
  }

  @GetMapping("/orderDetail/contract/{no}")
  public String getInvoice(@PathVariable String no,Model model){
    Map<String, Object> orderDetail = orderService.getOrderDetail(no);  //map
    model.addAttribute("order",orderDetail);

    String contractNo=orderDetail.get("contract_no").toString();  //map의 contract_no컬럼의 값을 String으로 가져옴
    model.addAttribute("contract",orderService.getContract(contractNo));
    return "/admin/contract-print";
  }

  //insert-19가지 필드
  @GetMapping("/addItem")
  public String addItem(Model model){
    //model.addAttribute("colors",itemService.listColors());
    return "/admin/addItem";
  }

  @PostMapping("/addItem")
  public String addItemProcess(@RequestBody@ModelAttribute("item") AdminItemDto item){
    itemService.setItem(item);
    return "redirect:/admin/itemManagement";
  }

  //updadte-16가지 필드
  @GetMapping("/itemDetail/updateItem/{code}")
  public String updateItem(@PathVariable String code, Model model){
    ItemMaster itemDetails = itemService.getItemDetails(code);
    model.addAttribute("item",itemDetails); //
    model.addAttribute("images",itemService.getItemImg(code)); //list
    model.addAttribute("stocks",itemService.getStockList(code));  //map
    model.addAttribute("colors",itemService.getStockDetails(code)); //map
    return "/admin/updateItem";
  }

  @PostMapping("/itemDetail/updateItem/{code}")
  public String updateItemProcess(@PathVariable String code,@RequestBody@ModelAttribute("item") AdminItemDto item){
    itemService.setItem(item);
    return "redirect:/admin/itemDetail";
  }
}
