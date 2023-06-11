package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class ItemMaster extends EssentialDate {
    @Id
    private String itemCode;                   // 상품 코드
    @Column(nullable=false)
    private String itemName;                   // 상품 이름
    @Column(nullable=false)
    private int itemPrice;                     // 상품 가격
    @Column(nullable=false)
    private String itemSize;                   // 상품 크기
    @Column(nullable=false)
    private int itemWeight;                    // 상품 무게
    @Column(nullable=false)
    private String itemWaterMethod;            // 직수 방식
    @Column(nullable=false)
    private String itemTankCapacity;           // 탱크 용량
    @Column(nullable=false)
    private String itemIntalType;              // 설치 방식
    @Column(nullable=false)
    private LocalDateTime itemMakeDate;        // 제조일
    @Column(nullable=false)
    private String itemFrom;                   // 제조사

    @Column(nullable=false)
    private String itemMasterImg;              // 대표 이미지
}
