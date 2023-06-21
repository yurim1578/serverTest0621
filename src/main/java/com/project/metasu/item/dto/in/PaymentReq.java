package com.project.metasu.item.dto.in;

import com.project.metasu.item.domain.entity.*;
import com.project.metasu.member.domain.entity.Member;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReq {
    private ContractReq contractReq;
    private DeliveryReq deliveryReq;
    private RentalReq rentalReq;
    private PayReq payReq;
    private OrderMasterReq orderMasterReq;
    private Member memberReq;
    private OrderDetail orderDetailReq;
    private ItemStock ItemStockReq;
    private Integer orderQty;

    @Getter
    @Setter
    public static class ContractReq {
        private String contractNo = "C_" + LocalDateTime.now();
        private String contractName;        // 계약자 이름
        private String contractPhone;       // 계약자 핸드폰 번호
        private String contractEmail;       // 계약자 이메일
        private String contractAddr1;       // 계약자 주소
        private String contractAddr2;       // 계약자 상세주소

        public Contract toEntity() {
            return Contract.builder()
                .contractNo(this.getContractNo())
                .contractName(this.getContractName())
                .contractPhone(this.getContractPhone())
                .contractEmail(this.getContractEmail())
                .contractAddr1(this.getContractAddr1())
                .contractAddr2(this.getContractAddr2())
                .contractStatus("CP") // 결제 대기
                .build();
        }
    }
    @Getter
    @Setter
    public static class DeliveryReq {
        private String deliveryNo = "D_" + LocalDateTime.now();
        private String deliveryName;        // 배달받는사람 이름
        private String deliveryPhone;       // 배달받는사람 핸드폰 번호
        private String deliveryAddr1;       // 배달 받는사람 주소
        private String deliveryAddr2;       // 배달 받는사람 상세주소
        private String deliveryStatus;      // 배달 상태코드
        private String installDate;      // LocalDate 원하는 설치 날짜
        private String installTimeCode;     // 원하는 설치 시간

        public Delivery toEntity() {
            return Delivery.builder()
                .deliveryNo(this.getDeliveryNo())
                .deliveryName(this.getDeliveryName())
                .deliveryPhone(this.getDeliveryPhone())
                .deliveryAddr1(this.getDeliveryAddr1())
                .deliveryAddr2(this.getDeliveryAddr2())
                .deliveryStatus("IP")
                .installDate(LocalDate.parse(this.getInstallDate(), DateTimeFormatter.ofPattern("yyyyMMdd")))
                .installTimeCode(this.getInstallTimeCode())
                .build();

        }
    }

    @Getter
    @Setter
    public static class RentalReq {
        private String rentalNo = "R_" + LocalDateTime.now();
        private String rentalPayAutoDate;
        private Boolean rentalPayAutoYn;
        private String rentalPeriod;
        private LocalDateTime rentalStartDate;
        private LocalDateTime rentalEndDate;
        private String rentalStatus;

        public Rental toEntity() {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime calculatedEndDate = now.plusYears(Long.parseLong(rentalPeriod));
            return Rental.builder()
                .rentalNo(this.getRentalNo())
                .rentalPayAutoDate(this.getRentalPayAutoDate())
                .rentalPayAutoYn(true)
                .rentalPeriod(this.getRentalPeriod())
                .rentalStartDate(now)
                .rentalEndDate(calculatedEndDate)
                .rentalStatus("RP")
                .build();
        }
    }

    @Getter
    @Setter
    public static class PayReq {
        private String paymentNo;
        private String paymentType;
        private String paymentCreditNumber;
        private String paymentAccount;
        private String paymentBank;
        private Integer paymentAmount;
        private String paymentStatus;

        public Payment toEntity() {
            return Payment.builder()
                .paymentNo("P_" + LocalDateTime.now())
                .paymentType(this.getPaymentType())
                .paymentCreditNumber(this.getPaymentCreditNumber())
                .paymentAccount(this.getPaymentAccount())
                .paymentBank(this.getPaymentBank())
                .paymentAmount(this.getPaymentAmount())
                .paymentStatus("PC") // 결제 완료
                .build();
        }
    }


    @Getter
    @Setter
    public static class OrderMasterReq {
        private String orderNo;
        private Boolean orderDiscountYn;
        private Integer orderAmount;
        private String orderStatus;

        /*public OrderMaster toEntity() {
            return OrderMaster.builder()
                .orderNo(this.getOrderNo())
                .orderDiscountYn(this.getOrderDiscountYn())
                .orderAmount(this.getOrderAmount())
                .orderStatus(this.getOrderStatus())
                .build();
        }*/
    }

    @Getter
    @Setter
    public static class OrderDetailReq {
        private OrderMaster orderMaster;
        private ItemStock itemStock;
        private Rental rental;

        public OrderDetail toEntity() {
            OrderDetail build = OrderDetail.builder()
                    .orderMaster(this.getOrderMaster())
                    .itemStock(this.getItemStock())
                    .rental(this.getRental())
                    .build();
            return build;

        }
    }
    @Getter
    @Setter
    public static class ItemStockReq {
          private String itemCode;
          private String itemColorCode;
    }

    @Getter
    @Setter
    public static class MemberReq {
        private Member member;
    }



}