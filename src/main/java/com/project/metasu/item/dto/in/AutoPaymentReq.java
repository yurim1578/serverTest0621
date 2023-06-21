package com.project.metasu.item.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.metasu.item.domain.entity.OrderDetail;
import com.project.metasu.item.domain.entity.Payment;
import com.project.metasu.item.domain.entity.Rental;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AutoPaymentReq {




                @JsonProperty("billingKey")
                private String billingKey;
                @JsonProperty("customerKey")
                private String customerKey;
                @JsonProperty("method")
                private String method;
                @JsonProperty("cardCompany")
                private String cardCompany;
                @JsonProperty("cardNumber")
                private String cardNumber;

                private String rentalNo;
                private LocalDateTime rentalStartDate;
                private LocalDateTime rentalEndDate;
                private String rentalPayAutoDate;
                private String rentalPeriod;
                private Boolean rentalPayAutoYn;
                private String rentalStatus;
                @Builder
                public AutoPaymentReq(String billingKey, String customerKey, String method, String cardCompany, String cardNumber, String rentalNo, LocalDateTime rentalStartDate, LocalDateTime rentalEndDate, String rentalPayAutoDate, String rentalPeriod, Boolean rentalPayAutoYn, String rentalStatus) {
                    this.billingKey = billingKey;
                    this.customerKey = customerKey;
                    this.method = method;
                    this.cardCompany = cardCompany;
                    this.cardNumber = cardNumber;
                    this.rentalNo = rentalNo;
                    this.rentalStartDate = rentalStartDate;
                    this.rentalEndDate = rentalEndDate;
                    this.rentalPayAutoDate = rentalPayAutoDate;
                    this.rentalPeriod = rentalPeriod;
                    this.rentalPayAutoYn = rentalPayAutoYn;
                    this.rentalStatus = rentalStatus;
                }

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
                            .billingKey(this.getBillingKey())
                            .customerKey(this.getCustomerKey())
                            .build();
                }

    // -----------------------------------------------------------------

                private Integer paymentAmount;
                private String paymentNo = "P_" + LocalDateTime.now();
                private String paymentStatus = "PS";







}

