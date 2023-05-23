package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class OrderDetail extends EssentialDate {
    @EmbeddedId
    private OrderDetailId orderDetailId;        // orderNo, itemBarCode 주문 번호, 상품 바코드
    @ManyToOne
    @JoinColumn(name = "rentalNo", nullable=false)
    private Rental rental_no;                   // 렌탈 번호
}
