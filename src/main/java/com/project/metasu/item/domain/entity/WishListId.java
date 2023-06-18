package com.project.metasu.item.domain.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class WishListId implements Serializable {

    private String memberId;     // 고객 아이디
    private String itemCode;     // 상품 코드
}
