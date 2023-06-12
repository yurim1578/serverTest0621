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
    private String itemCode;        // 상품 코드

    @MapsId("itemCode")
    @ManyToOne
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemCode", nullable = false, insertable = false, updatable = false)
    private ItemMaster itemMaster;

    @Id
    private String itemColorCode;   // 상품 색상 코드
}
