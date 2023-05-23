package com.project.metasu.item.domain.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class WishList {
    @EmbeddedId
    private WishListId wishListId; // memberId, itemCode 고객 아이디, 상품 코드
}
