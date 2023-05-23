package com.project.metasu.item.domain.entity;

import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.util.domain.EssentialDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Review extends EssentialDate {
    @Id
    private int reviewNo;                        // 리뷰 번호
    @ManyToOne
    @JoinColumn(name = "itemCode", nullable=false)
    private ItemMaster itemCode;                 // 상품 코드
    @Column(nullable=false)
    private String itemColorCode;                // 상품 색상 코드
    @ManyToOne
    @JoinColumn(name = "memberId", nullable=false)
    private Member memberId;                     // 고객 아이디
    @Column(length = 100, nullable=false)
    private String reviewTitle;                  // 리뷰 제목
    @Column(length = 2000, nullable=false)
    private String reviewContents;               // 리뷰 내용
    @Column(length = 2000)
    private String adminContents;                // 관리자 답글
    @Column(nullable=false)
    private int reviewScore;                     // 리뷰 별점
}
