package com.project.metasu.member.controller;

import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.repository.ImgRepository;
import com.project.metasu.item.repository.ItemMasterRepository;

import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.service.CategoryService;
import com.project.metasu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class CategoryController {

  private final ItemMasterRepository itemRepository;
  private final ImgRepository imgRepository;

  @Autowired
  private final CategoryService categoryService;
  @Autowired
  private final MemberService memberService;

  // 카테고리에 상품 전체 출력
  @GetMapping("/category")
  public String showCategoryItems(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "9") int pageSize2
      , @RequestParam(required = false) String capacity, @RequestParam(required = false) String type, @RequestParam(required = false) String from
      , @RequestParam(required = false) String method, Authentication authentication, @RequestParam(required = false) String sort) {
    if (authentication != null) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String memberId = userDetails.getUsername();
      System.out.println(memberId);
      Member member = memberService.getInfo(memberId);  // 로그인한 유저의 ID로 Member객체를 불러옴.
      model.addAttribute("member", member);
    }
    Page<ItemMaster> items = categoryService.getList(page, pageSize2, capacity, type, from, method, sort);

    int totalPage = items.getTotalPages();
    long totalElement = items.getTotalElements();
    int pageElement = items.getSize();
    int pageSize = pageSize2;


    model.addAttribute("form", from);
    model.addAttribute("capacity", capacity);
    model.addAttribute("type", type);
    model.addAttribute("method", method);
    model.addAttribute("totalPage", totalPage);
    model.addAttribute("totalElement", totalElement);
    model.addAttribute("pageElement", pageElement);
    model.addAttribute("items", items);
    model.addAttribute("currentPage", page);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("sort", sort);
    return "member/category";
  }

}

// imgRepository를 사용하여 이미지 작업을 수행
// 필요에 따라 imgRepository를 활용하여 이미지 관련 정보를 가져오거나 처리
// 예시: List<Image> images = imgRepository.findByProductId(productId);
//       model.addAttribute("images", images);

/*위의 코드에서는 ItemRepository를 주입하여 상품 목록을 조회하고,
 조회된 상품 목록을 products라는 이름의 모델 속성에 추가.
 Thymeleaf 템플릿에서 products 변수를 사용O*/








