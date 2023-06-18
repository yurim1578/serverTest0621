package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private String rentalNo;
    @MapsId("rentalNo")
    @ManyToOne
    @JoinColumn(name = "rentalNo")
    private Rental rental;                 // 렌탈 번호

    @Builder
    public OrderDetail(OrderMaster orderMaster, ItemStock itemStock, Rental rental) {
        this.orderMaster = orderMaster;
        this.orderNo = orderMaster.getOrderNo();
        itemStock.setSalesYn(true); // item_stock 재고 판매 여부 N에서 Y로 변경
        this.itemStock = itemStock;
        this.itemBarcode = itemStock.getItemBarcode();
        this.rentalNo = rental == null ? null : rental.getRentalNo();
        this.rental = rental;
    }
}
