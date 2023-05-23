package com.project.metasu.item.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Embeddable
public class OrderDetailId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "orderNo", nullable=false)
    private OrderMaster orderNo;                    // 주문 번호
    @ManyToOne
    @JoinColumn(name = "itemBarcode", nullable=false)
    private ItemStock itemBarcode;                  // 상품 바코드
}
