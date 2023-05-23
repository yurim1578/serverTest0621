package com.project.metasu.item.domain.entity;

import com.project.metasu.util.converter.BooleanToYnConverter;
import com.project.metasu.util.domain.EssentialDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class OrderMaster extends EssentialDate {
    @Id
    private String orderNo;                                        // 주문 번호
    @ManyToOne
    @JoinColumn(name = "contractNo", nullable=false)
    private Contract contractNo;                                   // 계약 번호
    @ManyToOne
    @JoinColumn(name = "deliveryNo", nullable=false)
    private Delivery deliveryNo;                                   // 배달 번호
    @ManyToOne
    @JoinColumn(name = "rentalNo")
    private Rental rentalNo;                                       // 렌탈 번호
    @ManyToOne
    @JoinColumn(name = "paymentNo", nullable=false)
    private Payment paymentNo;                                     // 결제 번호
    @Convert(converter = BooleanToYnConverter.class) @Column(nullable=false)
    private Boolean orderDiscountYn;                               // 할인 여부
    @Column(nullable=false)
    private int orderAmount;                                       // 주문 총액
    @Column(nullable=false)
    private String orderStatus;                                    // 주문 상태
}
