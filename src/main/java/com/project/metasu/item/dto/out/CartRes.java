package com.project.metasu.item.dto.out;

import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.member.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CartRes {
    private String itemMasterImg; // 고객 아이디
    private String itemName; // 상품 코드
    private int itemPrice; // 상품 색상 코드
    private int cartQty; // 장바구니 수량

    public CartRes(String itemMasterImg, String itemName, int itemPrice, int cartQty) {
        this.itemMasterImg = itemMasterImg;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.cartQty = cartQty;
    }
}
