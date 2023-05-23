package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;

import javax.persistence.*;

@Entity
public class ItemDetail extends EssentialDate {
    @EmbeddedId
    private ItemDetailId itemDetailId; //itemCode, itemColorCode 상품코드, 상품 색상코드
}
