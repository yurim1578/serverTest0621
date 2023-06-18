package com.project.metasu.item.domain.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemDetailId implements Serializable {
    private String itemCode;          // 상품 코드
    @Column(nullable=false)
    private String itemColorCode;     // 상품 색상 코드
}
