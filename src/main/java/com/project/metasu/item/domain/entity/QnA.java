package com.project.metasu.item.domain.entity;

import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnA extends EssentialDate {
    @Id
    private int qnaNo;                       // q&a 번호
    @Column(nullable=false)
    private String qnaOptionCode;            // q&a 옵션 코드
    @ManyToOne
    @JoinColumn(name = "memberId", nullable=false)
    private Member memberId;                 // 고객 아이디
    @Column(nullable=false)
    private String qnaStatus;                // q&a 상태 코드
    @Column(length = 100, nullable=false)
    private String qnaTitle;                 // q&a 제목
    @Column(length = 2000, nullable=false)
    private String qnaContents;              // q&a 내용
    @Column(length = 2000)
    private String qnaAdminContents;         // q&a 관리자 답글
}
