package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ItemImg extends EssentialDate {
    @EmbeddedId
    private ItemDetailId itemDetailId;  // itemCode, itemColorCode 상품 코드, 상품 색상 코드
    @Column(nullable=false)
    private String itemMasterImg;       // 상품 대표 이미지
    private String itemImg1;            // 상품 이미지 1
    private String itemImg2;            // 상품 이미지 2
    private String itemImg3;            // 상품 이미지 3
}
