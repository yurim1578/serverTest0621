package com.project.metasu.item.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Embeddable
public class ItemDetailId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "itemCode", nullable=false)
    private ItemMaster itemCode;          // 상품 코드
    @Column(nullable=false)
    private String itemColorCode;         // 상품 색상 코드
}
