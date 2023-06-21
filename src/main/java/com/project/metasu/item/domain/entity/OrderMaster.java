package com.project.metasu.item.domain.entity;

import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.util.converter.BooleanToYnConverter;
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
public class OrderMaster extends EssentialDate {
    @Id
    private String orderNo;                                        // 주문 번호

    private String memberId;                                       // 고객 아이디
    private String contractNo;                                     // 계약 번호
    private String deliveryNo;                                     // 배달 번호
    private String rentalNo;                                       // 렌탈 번호
    private String paymentNo;                                      // 결제 번호

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId",nullable=false, insertable = false, updatable = false)
    private Member member;

    @MapsId("contractNo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contractNo",nullable=false, insertable = false, updatable = false)
    private Contract contract;                                   // 계약 번호
    @MapsId("deliveryNo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="deliveryNo",nullable=false, insertable = false, updatable = false)
    private Delivery delivery;                                   // 배달 번호
    @MapsId("rentalNo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rentalNo",nullable=false, insertable = false, updatable = false)
    private Rental rental;                                       // 렌탈 번호
    @MapsId("paymentNo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="paymentNo",nullable=false, insertable = false, updatable = false)
    private Payment payment;                                     // 결제 번호
    @Convert(converter = BooleanToYnConverter.class) @Column(nullable=false)
    private Boolean orderDiscountYn;                             // 할인 여부
    @Column(nullable=false)
    private int orderAmount;                                     // 주문 총액
    @Column(nullable=false)
    private String orderStatus;                                  // 주문 상태
    @Builder
    public OrderMaster(String orderNo, Member member, Contract contract, Delivery delivery, Rental rental, Payment payment, Boolean orderDiscountYn, int orderAmount, String orderStatus) {
        this.orderNo = orderNo;
        this.memberId = member.getMemberId();
        this.contractNo = contract.getContractNo();
        this.deliveryNo = delivery.getDeliveryNo();
        this.rentalNo = rental == null ? null : rental.getRentalNo();
        this.paymentNo = payment.getPaymentNo();
        this.orderDiscountYn = orderDiscountYn;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
    }
}
