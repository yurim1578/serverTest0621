package com.project.metasu.item.dto.in;

import com.project.metasu.item.domain.entity.Cart;
import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RentalReq {
    private String itemCode;            // 상품 코드
    private String itemColorCode;       // 상품 색상 코드
    private String memberId;            // 고객 아이디
    private Integer rentalPeriod;       // 렌탈 약정 기간
    private Integer rentalPayAutoDate;  // 렌탈 자동 결제일

    @Builder
    public RentalReq(String itemCode, String itemColorCode, String memberId, Integer rentalPeriod, Integer rentalPayAutoDate) {
        this.itemCode = itemCode;
        this.itemColorCode = itemColorCode;
        this.memberId = memberId;
        this.rentalPeriod = rentalPeriod;
        this.rentalPayAutoDate = rentalPayAutoDate;
    }
}
