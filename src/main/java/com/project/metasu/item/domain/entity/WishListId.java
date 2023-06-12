package com.project.metasu.item.domain.entity;

import com.project.metasu.member.domain.entity.Member;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Embeddable
public class WishListId implements Serializable {

    private String memberId;     // 고객 아이디
    private String itemCode;     // 상품 코드
}
