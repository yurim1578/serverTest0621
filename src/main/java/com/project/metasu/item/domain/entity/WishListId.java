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
    @ManyToOne
    @JoinColumn(name = "memberId", nullable=false)
    private Member memberId;                     // 고객 아이디

    @ManyToOne
    @JoinColumn(name = "itemCode", nullable=false)
    private ItemMaster itemCode;                 // 상품 코드
}
