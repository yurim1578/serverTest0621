package com.project.metasu.item.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RentalRes {
    private String itemCode;            // 상품 코드
    private String itemColorCode;       // 상품 색상 코드
    private String memberId;            // 고객 아이디
    private Integer rentalPeriod;       // 렌탈 약정 기간
    private Integer rentalPayAutoDate;  // 렌탈 자동 결제일
    private String itemName;
    private String itemColorCodeName;
    private Integer itemPrice;

    @Builder
    public RentalRes(String itemCode, String itemColorCode, String memberId, Integer rentalPeriod, Integer rentalPayAutoDate, String itemName, String itemColorCodeName, Integer itemPrice) {
        this.itemCode = itemCode;
        this.itemColorCode = itemColorCode;
        this.memberId = memberId;
        this.rentalPeriod = rentalPeriod;
        this.rentalPayAutoDate = rentalPayAutoDate;
        this.itemName = itemName;
        this.itemColorCodeName = itemColorCodeName;
        this.itemPrice = itemPrice;
    }
}
