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
public class Payment extends EssentialDate {
    @Id
    private String paymentNo;                // 결제 번호
    @Column(nullable=false)
    private String paymentType ;             // 결제 방식
    private String paymentCreditNumber;      // 카드 번호
    private String paymentAccount;           // 계좌 번호
    @Column(nullable=false)
    private String paymentBank;              // 결제 은행
    @Column(nullable=false)
    private int paymentAmount;               // 결제 금액
    @Column(nullable=false)
    private String paymentStatus;            // 결제 상태

    @Builder
    public Payment(String paymentNo, String paymentType, String paymentCreditNumber, String paymentAccount, String paymentBank, int paymentAmount, String paymentStatus) {
        this.paymentNo = paymentNo;
        this.paymentType = paymentType;
        this.paymentCreditNumber = paymentCreditNumber;
        this.paymentAccount = paymentAccount;
        this.paymentBank = paymentBank;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
    }
}
