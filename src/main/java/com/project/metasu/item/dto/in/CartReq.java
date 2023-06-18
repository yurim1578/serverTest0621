package com.project.metasu.item.dto.in;

import com.project.metasu.item.domain.entity.Cart;
import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CartReq {
    private String itemCode;        // 상품 코드
    private String itemColorCode;   // 상품 색상 코드
    private String memberId;        // 고객 아이디
    private Integer cartQty;        // 장바구니 수량

    @Builder
    public CartReq(String itemCode, String itemColorCode, String memberId, Integer cartQty) {
        this.itemCode = itemCode;
        this.itemColorCode = itemColorCode;
        this.memberId = memberId;
        this.cartQty = cartQty;
    }
    public Cart toEntity(ItemMaster itemMaster, String itemColorCode, Member member, Integer cartQty) {
        Cart build = Cart.builder()
                .itemMaster(itemMaster)
                .itemColorCode(itemColorCode)
                .member(member)
                .cartQty(cartQty)
                .build();
        return build;
    }
}
