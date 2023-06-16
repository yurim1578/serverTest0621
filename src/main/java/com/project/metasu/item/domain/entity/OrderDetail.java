package com.project.metasu.item.domain.entity;

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
@IdClass(OrderDetailId.class)
public class OrderDetail extends EssentialDate {
    @Id
    private String orderNo;                  // 주문 번호

    @MapsId("orderNo")
    @ManyToOne
    @JoinColumn(name = "orderNo", nullable = false, insertable = false, updatable = false)
    private OrderMaster orderMaster;

    @Id
    private String itemBarcode;              // 상품 바코드

    @MapsId("itemBarcode")
    @ManyToOne
    @JoinColumn(name = "itemBarcode", nullable = false, insertable = false, updatable = false)
    private ItemStock itemStock;

    @ManyToOne
    @JoinColumn(name = "rentalNo")
    private Rental rental_no;                 // 렌탈 번호

    @Builder
    public OrderDetail(String orderNo, OrderMaster orderMaster, String itemBarcode, ItemStock itemStock, Rental rental_no) {
        this.orderNo = orderNo;
        this.orderMaster = orderMaster;
        this.itemBarcode = itemBarcode;
        this.itemStock = itemStock;
        this.rental_no = rental_no;
    }
}
