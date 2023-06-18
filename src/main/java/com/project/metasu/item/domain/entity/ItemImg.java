package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(ItemDetailId.class)
public class ItemImg extends EssentialDate {
    @Id
    private String itemCode;            // 상품 코드

    @MapsId("itemCode")
    @ManyToOne
    @JoinColumn(name = "itemCode", nullable = false, insertable = false, updatable = false)
    private ItemMaster itemMaster;

    @Id
    private String itemColorCode;       // 상품 색상 코드
    @Column(nullable=false)
    private String itemMasterImg;       // 상품 대표 이미지
    private String itemImg1;            // 상품 이미지 1
    private String itemImg2;            // 상품 이미지 2
    private String itemImg3;            // 상품 이미지 3

    @Builder
    public ItemImg(String itemCode, ItemMaster itemMaster, String itemColorCode, String itemMasterImg, String itemImg1, String itemImg2, String itemImg3) {
        this.itemCode = itemCode;
        this.itemMaster = itemMaster;
        this.itemColorCode = itemColorCode;
        this.itemMasterImg = itemMasterImg;
        this.itemImg1 = itemImg1;
        this.itemImg2 = itemImg2;
        this.itemImg3 = itemImg3;
    }
}
