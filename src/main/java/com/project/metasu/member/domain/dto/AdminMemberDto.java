package com.project.metasu.member.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter@ToString
public class AdminMemberDto {
  String memberId;
  String memberPw;
  String memberName;
  String memberAddr1;
  String memberAddr2;
  String memberPhone;
  String memberEmail;
 LocalDateTime createdDate;
  int orderNum;
  int reviewNum;

  @Builder
  public AdminMemberDto(String memberId, String memberPw, String memberName, String memberAddr1, String memberAddr2, String memberPhone, String memberEmail, LocalDateTime createdDate, int orderNum, int reviewNum){
    this.memberId = memberId;
    this.memberPw = memberPw;
    this.memberName = memberName;
    this.memberAddr1 =memberAddr1;
    this.memberAddr2 =memberAddr2;
    this.memberPhone=memberPhone;
    this.memberEmail = memberEmail;
    this.createdDate = createdDate;
    this.orderNum = orderNum;
    this.reviewNum = reviewNum;
  }

  public AdminMemberDto() {
  }
}
