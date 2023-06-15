package com.project.metasu.item.domain.entity;

import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.util.domain.CommonCodeDetail;
import com.project.metasu.util.domain.CommonCodeMaster;
import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends EssentialDate {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewNo;                        // 리뷰 번호
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
    private String reviewMasterImg;              // 리뷰 마스터 이미지

}
