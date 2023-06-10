package com.project.metasu.item.domain.entity;

import com.project.metasu.member.domain.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(WishListId.class)
public class WishList {
    @Id
    private Member memberId;        // 고객 아이디
    @Id
    private ItemMaster itemCode;    // 상품 코드
}
