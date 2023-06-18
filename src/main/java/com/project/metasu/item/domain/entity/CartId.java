package com.project.metasu.item.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class CartId implements Serializable {

    private String memberId;                 // 고객 아이디
    private String itemCode;                 // 상품 코드

    @Column(nullable=false)
    private String itemColorCode;            // 상품 색상 코드
}
