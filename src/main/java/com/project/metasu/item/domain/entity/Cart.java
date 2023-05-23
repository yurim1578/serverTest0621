package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Cart extends EssentialDate {
    @EmbeddedId
    private WishListId wishListId; //memberId, itemCode 고객아이디, 상품코드
    @Column(nullable=false)
    private int cartQty; // 장바구니 수량
}
