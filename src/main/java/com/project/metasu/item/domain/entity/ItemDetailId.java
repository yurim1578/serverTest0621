package com.project.metasu.item.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemDetailId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "itemCode", nullable=false)
    private ItemMaster itemCode;          // 상품 코드
    @Column(nullable=false)
    private String itemColorCode;         // 상품 색상 코드
}
