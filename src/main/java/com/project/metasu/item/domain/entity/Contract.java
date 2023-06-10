package com.project.metasu.item.domain.entity;

import com.project.metasu.util.domain.EssentialDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contract extends EssentialDate {
    @Id
    private String contractNo;               // 계약 번호
    @Column(nullable=false)
    private String contractName;             // 계약자 이름
    @Column(nullable=false)
    private String contractPhone;            // 계약자 핸드폰 번호
    @Email
    @Column(nullable=false)
    private String contractEmail;            // 계약자 이메일
    @Column(nullable=false)
    private String contractZipCode;          // 계약자 우편번호
    @Column(nullable=false)
    private String contractAddr1;            // 계약자 주소
    private String contractAddr2;            // 계약자 상세주소
    private String contractRecieve;          // 계약 영수증
    @Column(nullable=false)
    private String contractStatus;           // 계약 상태코드
}
