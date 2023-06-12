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
    private String itemCode;      // 상품 코드

    @MapsId("itemCode")
    @ManyToOne
    @JoinColumn(name = "itemCode", nullable = false, insertable = false, updatable = false)
    private ItemMaster itemMaster;

    @Id
    private String itemColorCode; // 상품 색상 코드

    @Id
    private String memberId;      // 고객 아이디

    @MapsId("memberId")
    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false, insertable = false, updatable = false)
    private Member member;

    @Column(nullable=false)
    private int cartQty;          // 장바구니 수량

    @Builder
    public Cart(ItemMaster itemMaster, String itemColorCode, Member member, int cartQty) {
        this.itemCode = itemMaster.getItemCode();
        this.itemMaster = itemMaster;
        this.itemColorCode = itemColorCode;
        this.memberId = member.getMemberId();
        this.member = member;
        this.cartQty = cartQty;
    }
    public void setCartQty(int cartQty) {
        this.cartQty = cartQty;
    }
}
