package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends EssentialDate {
    @Id
    private String deliveryNo;                 // 배달 번호
    @Column(nullable=false)
    private String deliveryName;               // 배달받는사람 이름
    @Column(nullable=false)
    private String deliveryPhone;              // 배달받는사람 핸드폰 번호
    @Column(nullable=false)
    private String deliveryAddr1;              // 배달 받는사람 주소
    private String deliveryAddr2;              // 배달 받는사람 상세주소
    @Column(nullable=false)
    private String deliveryStatus;             // 배달 상태코드
    @Column(nullable=false)
    private LocalDate installDate;             // 원하는 설치 날짜
    @Column(nullable=false)
    private String installTimeCode;             // 원하는 설치 시간

    @Builder
    public Delivery(String deliveryNo, String deliveryName, String deliveryPhone, String deliveryAddr1, String deliveryAddr2, String deliveryStatus, LocalDate installDate, String installTimeCode) {
        this.deliveryNo = deliveryNo;
        this.deliveryName = deliveryName;
        this.deliveryPhone = deliveryPhone;
        this.deliveryAddr1 = deliveryAddr1;
        this.deliveryAddr2 = deliveryAddr2;
        this.deliveryStatus = "IP"; // 설치 대기
        this.installDate = installDate;
        this.installTimeCode = installTimeCode;
    }
}
