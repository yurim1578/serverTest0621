package com.project.metasu.item.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberRes {
    private String memberName;          // 고객 이름
    private String memberPhone;         // 고객 번호
    private String memberEmail;
    private String memberZipCode;       // 고객 우편번호
    private String memberAddr1;         // 고객 상세주소1
    private String memberAddr2;         // 고객 상세주소2

    @Builder
    public MemberRes(String memberName, String memberPhone, String memberEmail, String memberZipCode, String memberAddr1, String memberAddr2) {
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberZipCode = memberZipCode;
        this.memberAddr1 = memberAddr1;
        this.memberAddr2 = memberAddr2;
    }
}
