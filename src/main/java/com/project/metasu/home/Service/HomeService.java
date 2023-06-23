package com.project.metasu.home.Service;

import com.project.metasu.home.Dto.HomeAddCartDto;
import com.project.metasu.home.Dto.HomeItemDto;
import com.project.metasu.item.domain.entity.Cart;
import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.repository.CartRepository;
import com.project.metasu.item.repository.ItemMasterRepository;
import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {
  private final MemberService memberService;
  private final CartRepository cartRepository;
  private final ItemMasterRepository itemMasterRepository;


  public List<HomeItemDto> findAllItems() {
    return itemMasterRepository.findHomeItem();
  }
  public boolean itemExistInCart(HomeAddCartDto dto){
//    Member member = memberService.getCurrentUser();
    Member member = memberService.findMemberById("hye").toEntity();


    return cartRepository.existsByItemCodeAndItemColorCodeAndMemberId(dto.getItemCode(), dto.getItemColorCode(), member.getMemberId());
  }

  @Transactional
  public void addItemToCart(HomeAddCartDto dto) {
//   Member member = memberService.getCurrentUser();
    Member member = memberService.findMemberById("hye").toEntity();

    ItemMaster itemMaster = this.findItemByItemCode(dto.getItemCode());
    int defaultQuantity = 1;
    Cart cart = Cart.builder()
        .itemMaster(itemMaster)
        .cartQty(defaultQuantity)
        .itemColorCode(dto.getItemColorCode())
        .member(member)
        .build();
    cartRepository.save(cart);
  }
//  public Long countTotalNumberInCart(){
////    Member member = memberService.getCurrentUser();
//    Member member = memberService.findMemberById("nhu").toEntity();
//    return (long) cartRepository.findAllByMemberId(member.getMemberId()).size();
//  }

  public Long countTotalNumberInCart(){
//    Member member = memberService.getCurrentUser();
    Member member = memberService.findMemberById("hye").toEntity();
    return cartRepository.countAllByMember(member);
  }

  public ItemMaster findItemByItemCode(String itemCode) {
    return itemMasterRepository.findById(itemCode).orElseThrow(() -> new RuntimeException("NOT FOUND"));
  }

  public ItemMaster findItemByItemName(String itemName) {
    return itemMasterRepository.findByItemName(itemName);
  }

  public List<ItemMaster> getBestSellerItem(){
    PageRequest pageRequest = PageRequest.of(0, 5);
    return itemMasterRepository.findTop5BestSellerItem(pageRequest);
  }
  //private HomeItemDto convertEntityToDto(ItemMaster itemMaster) {
  //return HomeItemDto.builder()
    //  .itemCode(itemMaster.getItemCode())
      //.itemName(itemMaster.getItemName())
      //.itemPrice(itemMaster.getItemPrice())
      //.itemMasterImg(itemMaster.getItemMasterImg())
      //.itemColorCode(itemMaster.getItemColorCode())
      //.createdDate(itemMaster.getItemCreatedDate())
      //.build();
//}
}