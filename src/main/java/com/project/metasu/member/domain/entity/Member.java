package com.project.metasu.member.domain.entity;

import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends EssentialDate {
    @Id
    private String memberId;
    @Column(nullable=false)
    private String memberPw;
    @Column(nullable=false)
    private String memberName;
    @Email @Column(nullable=false)
    private String memberEmail;
    @Column(nullable=false)
    private String memberPhone;
    @Column(nullable=false)
    private String memberZipCode;
    @Column(nullable=false)
    private String memberAddr1;
    private String memberAddr2;
    @Column(nullable=false)
    private String memberAuth;

    @Builder
    public Member(String memberId, String memberPw, String memberName, String memberEmail, String memberPhone, String memberZipCode, String memberAddr1, String memberAddr2, String memberAuth) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberZipCode = memberZipCode;
        this.memberAddr1 = memberAddr1;
        this.memberAddr2 = memberAddr2;
        this.memberAuth = memberAuth;
    }
}
