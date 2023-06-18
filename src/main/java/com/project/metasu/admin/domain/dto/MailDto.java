package com.project.metasu.admin.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
public class MailDto {
  private String email; //계약자 이메일
  private String name;  //계약자 이름 ->이메일에 명시
  private String contractNo; //계약 번호 ->이메일에 명시
  private String paymentAmount;
  private String rentalEndDate; //렌탈 만료일
  private String rentalDate; //렌탈 정기 납부일
}
