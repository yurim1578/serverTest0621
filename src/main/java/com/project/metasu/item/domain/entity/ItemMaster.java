package com.project.metasu.item.domain.entity;

import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String item_desc;                  // 상품 상세설명
    @Column(nullable=false)
    private String itemMasterImg;              // 대표 이미지

    @Builder
    public ItemMaster(String itemCode, String itemName, int itemPrice, String itemSize, int itemWeight, String itemWaterMethod, String itemTankCapacity, String itemIntalType, LocalDateTime itemMakeDate, String itemFrom, String item_desc, String itemMasterImg) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemSize = itemSize;
        this.itemWeight = itemWeight;
        this.itemWaterMethod = itemWaterMethod;
        this.itemTankCapacity = itemTankCapacity;
        this.itemIntalType = itemIntalType;
        this.itemMakeDate = itemMakeDate;
        this.itemFrom = itemFrom;
        this.item_desc = item_desc;
        this.itemMasterImg = itemMasterImg;
    }
}
