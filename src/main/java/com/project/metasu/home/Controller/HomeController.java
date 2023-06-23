package com.project.metasu.home.Controller;

import com.project.metasu.home.Dto.HomeAddCartDto;
import com.project.metasu.home.Dto.HomeItemDto;
import com.project.metasu.home.Service.HomeService;
import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
  private final HomeService homeService;
  private final ItemService itemService;

  @GetMapping("/main")
  public String home(Model model, String memberId) {
    List<HomeItemDto> itemMaster = homeService.findAllItems().stream().limit(8).collect(Collectors.toList());
    model.addAttribute("itemLists", itemMaster);
    model.addAttribute("itemSubmit", new HomeAddCartDto());
    model.addAttribute("totalCart", homeService.countTotalNumberInCart());
//    model.AddAttribute("totalCart", itemService.findCart(memberId));
    model.addAttribute("bestSeller", homeService.getBestSellerItem());
    return "/home/home";
  }

  @PostMapping("/add-item")
  public String addItem(@ModelAttribute HomeAddCartDto dto, RedirectAttributes redirectAttributes) {
    if (homeService.itemExistInCart(dto)) {
      redirectAttributes.addFlashAttribute("msg", "장바구니에 이미 담아있습니다.");
    } else {
      homeService.addItemToCart(dto);
      redirectAttributes.addFlashAttribute("msg", "장바구니에 추가되었습니다.");
    }
    return "redirect:/home/main";
  }

  @GetMapping("/cart/{memberId}")
  public String cart(@PathVariable("memberId") String memberId, Model model) {
    ResponseEntity cartDto = itemService.findCart(memberId);    // memberId의 장바구니
    model.addAttribute("dto2", cartDto.getBody());
    return "/item/shop-cart";
  }

//  @PostMapping("/add-item")
//  public String addItem(@ModelAttribute HomeAddCartDto dto){
//    //todo uncomment and add authentication to redirect if user is logged in
////    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////    if (authentication instanceof AnonymousAuthenticationToken) return "redirect:/login";
//    homeService.addItemToCart(dto);
//    return "redirect:/home/main";
//  }


  @GetMapping("/info/{itemCode}")
  public String homeItemInfo(Model model,
                             @PathVariable String itemCode) {
    ItemMaster itemInfo = homeService.findItemByItemCode(itemCode);
    model.addAttribute("itemInfo", itemInfo);
    return "/item/info/{itemCode}";
  }

  //TODO already done at SecurityConfig configure method.
//  @PostMapping("/logout")
//  public String logout(HttpServletRequest req) { // HttpServletResponse resp
//    HttpSession session = req.getSession(false);// 세션이 없으면 null, 있으면 세션을 반환
//    if (session != null) {
//      session.invalidate();
//    }
//    return "redirect:/";
//  }
}

