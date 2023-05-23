package com.project.metasu.member.domain.entity;

import com.project.metasu.util.domain.EssentialDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
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
}
