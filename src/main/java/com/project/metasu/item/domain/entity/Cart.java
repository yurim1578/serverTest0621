package com.project.metasu.item.domain.entity;

import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(CartId.class)
public class Cart extends EssentialDate {
    @Id
    private Member memberId; // 고객 아이디
    @Id
    private ItemMaster itemCode; // 상품 코드
    @Id
    private String itemColorCode; // 상품 색상 코드

    @Column(nullable=false)
    private int cartQty; // 장바구니 수량

    @Builder
    public Cart(Member memberId, ItemMaster itemCode, String itemColorCode, int cartQty) {
        this.memberId = memberId;
        this.itemCode = itemCode;
        this.itemColorCode = itemColorCode;
        this.cartQty = cartQty;
    }
    public void setCartQty(int cartQty) {
        this.cartQty = cartQty;
    }
}
