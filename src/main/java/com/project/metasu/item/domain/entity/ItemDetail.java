package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(ItemDetailId.class)
public class ItemDetail extends EssentialDate {
    @Id
    private ItemMaster itemCode; // 상품 코드
    @Id
    private String itemColorCode; // 상품 색상 코드
}
