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
    private ItemMaster itemCode;        // 상품 코드
    @Id
    private String itemColorCode;       // 상품 색상 코드
    @Column(nullable=false)
    private String itemMasterImg;       // 상품 대표 이미지
    private String itemImg1;            // 상품 이미지 1
    private String itemImg2;            // 상품 이미지 2
    private String itemImg3;            // 상품 이미지 3
}
