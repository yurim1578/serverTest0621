package com.project.metasu.item.domain.entity;

import com.project.metasu.util.converter.BooleanToYnConverter;
import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rental extends EssentialDate {
    @Id
    private String RentalNo;                                    // 렌탈 번호
    @Column(nullable=false)
    private String RentalPayAutoDate;                           // 렌탈 자동 결제일
    @Convert(converter = BooleanToYnConverter.class)
    @Column(nullable=false)
    private Boolean RentalPayAutoYn;                            // 렌탈 자동 결제 유무
    @Column(nullable=false)
    private String RentalPeriod;                                // 렌탈 약정기간
    @Column(nullable=false)
    private LocalDateTime RentalStartDate;                      // 렌탈 시작일
    @Column(nullable=false)
    private LocalDateTime RentalEndDate;                        // 렌탈 종료일
    @Column(nullable=false)
    private String RentalStatus;                                // 렌탈 상태코드
    @Column
    private String billingKey;                                  // 빌링 키 (토스 정기결제)
    @Column
    private String customerKey;                                 // 커스텀 키 (토스 정기결제)

    @Builder
    public Rental(String rentalNo, String rentalPayAutoDate, Boolean rentalPayAutoYn, String rentalPeriod, LocalDateTime rentalStartDate, LocalDateTime rentalEndDate, String rentalStatus, String billingKey, String customerKey) {
        this.RentalNo = rentalNo;
        this.RentalPayAutoDate = rentalPayAutoDate;
        this.RentalPayAutoYn = rentalPayAutoYn;
        this.RentalPeriod = rentalPeriod;
        this.RentalStartDate = rentalStartDate;
        this.RentalEndDate = rentalEndDate;
        this.RentalStatus = rentalStatus;
        this.billingKey = billingKey;
        this.customerKey = customerKey;
    }
}
