package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(OrderDetailId.class)
public class OrderDetail extends EssentialDate {
    @Id
    private OrderMaster orderNo;                // 주문 번호
    @Id
    private ItemStock itemBarcode;              // 상품 바코드

    @ManyToOne
    @JoinColumn(name = "rentalNo")
    private Rental rental_no;                   // 렌탈 번호
}
