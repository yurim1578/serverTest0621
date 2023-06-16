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
import javax.validation.constraints.NotNull;
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
    private Boolean RentalRentalPayAutoYn;                      // 렌탈 자동 결제 유무
    @Column(nullable=false)
    private String RentalPeriod;                                // 렌탈 약정기간
    @Column(nullable=false)
    private LocalDateTime RentalStartDate;                      // 렌탈 시작일
    @Column(nullable=false)
    private LocalDateTime RentalEndDate;                        // 렌탈 종료일
    @Column(nullable=false)
    private String RentalStatus;

    @Builder
    public Rental(String rentalNo, String rentalPayAutoDate, Boolean rentalRentalPayAutoYn, String rentalPeriod, LocalDateTime rentalStartDate, LocalDateTime rentalEndDate, String rentalStatus) {
        RentalNo = rentalNo;
        RentalPayAutoDate = rentalPayAutoDate;
        RentalRentalPayAutoYn = rentalRentalPayAutoYn;
        RentalPeriod = rentalPeriod;
        RentalStartDate = rentalStartDate;
        RentalEndDate = rentalEndDate;
        RentalStatus = rentalStatus;
    }

}
