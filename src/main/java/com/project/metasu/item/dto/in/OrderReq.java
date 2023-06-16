package com.project.metasu.item.dto.in;

import com.project.metasu.item.domain.entity.Cart;
import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.domain.entity.OrderMaster;
import com.project.metasu.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class OrderReq {
    private String itemCode;
    private String itemColorCode;
    private String memberId;
    private Integer orderQty;
    private String salesYn;

    // payment req
    private String paymentAccount;
    private Integer paymentAmount;
    private String paymentBank;
    private String paymentCreditNumber;


    // itemBarcode 찾아오는 builder
    @Builder
    public OrderReq(String itemCode, String itemColorCode, String memberId, Integer orderQty, String salesYn) {
        this.itemCode = itemCode;
        this.itemColorCode = itemColorCode;
        this.memberId = memberId;
        this.salesYn = salesYn;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }


    public OrderMaster toEntity(ItemMaster itemMaster, String itemColorCode, Member member, Integer orderQty, String itemBarcode) {
        return null;
    }
}
