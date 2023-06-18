package com.project.metasu.item.domain.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderDetailId implements Serializable {
    private String orderNo;                    // 주문 번호
    private String itemBarcode;                // 상품 바코드
}
