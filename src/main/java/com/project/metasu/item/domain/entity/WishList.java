package com.project.metasu.item.domain.entity;

import com.project.metasu.member.domain.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(WishListId.class)
public class WishList {
    @Id
    private String memberId;    // 고객 아이디

    @MapsId("memberId")
    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false, insertable = false, updatable = false)
    private Member member;

    @Id
    private String itemCode;    // 상품 코드

    @MapsId("itemCode")
    @ManyToOne
    @JoinColumn(name = "itemCode", nullable = false, insertable = false, updatable = false)
    private ItemMaster itemMaster;
}
