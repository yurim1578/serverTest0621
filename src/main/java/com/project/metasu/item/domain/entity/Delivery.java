package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    private String deliveryZipCode;            // 배달받는사람 우편번호
    @Column(nullable=false)
    private String deliveryAddr1;              // 배달 받는사람 주소
    private String deliveryAddr2;              // 배달 받는사람 상세주소
    @Column(nullable=false)
    private String deliveryStatus;             // 배달 상태코드
    @Column(nullable=false)
    private LocalDateTime deliveryStartDate;   // 배달 시작일
    private LocalDateTime deliveryEndDate;     // 배달 종료일
}
