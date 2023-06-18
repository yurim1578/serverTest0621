package com.project.metasu.member.dto;

import com.project.metasu.member.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberDto {
  private String memberId;
  private String memberPw;
  private String memberName;
  private String memberEmail;
  private String memberPhone;
  private String memberAuth;
  private String memberAddr1;
  private String memberAddr2;

  // Member를 인자로 받는 생성자
  public MemberDto(Member member) {
    this.memberId = member.getMemberId();
    this.memberPw = member.getMemberPw();
    this.memberName = member.getMemberName();
    this.memberEmail = member.getMemberEmail();
    this.memberPhone = member.getMemberPhone();
    this.memberAuth = member.getMemberAuth();
    this.memberAddr1 = member.getMemberAddr1();
    this.memberAddr2 = member.getMemberAddr2();
  }

  public Member toEntity() {
    return Member.builder()
        .memberId(memberId)
        .memberPw(memberPw)
        .memberName(memberName)
        .memberEmail(memberEmail)
        .memberPhone(memberPhone)
        .memberAuth(memberAuth)
        .memberAddr1(memberAddr1)
        .memberAddr2(memberAddr2)
        .build();
  }




}
/*
  @Builder
  public MemberDto(String memberId, String memberPw, String memberName, String memberEmail, String memberPhone, String memberAuth, String memberAddr1) {
    this.memberId = memberId;
    this.memberPw = memberPw;
    this.memberName = memberName;
    this.memberEmail = memberEmail;
    this.memberPhone = memberPhone;
    this.memberAuth = memberAuth;
    this.memberAddr1 = memberAddr1;
  }*/

