package com.project.metasu.member.service;

import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.dto.MemberDto;
import com.project.metasu.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService  {


  private final MemberRepository memberRepository;
  @Autowired
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.memberRepository = memberRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }


  public void insert(MemberDto memberDto) {
    memberDto.setMemberPw(bCryptPasswordEncoder.encode(memberDto.getMemberPw()));
    memberDto.setMemberAuth("MB");
    memberDto.setMemberAddr2("home");
    memberRepository.save(memberDto.toEntity());
  }

  public MemberDto findMemberById(String memberId) {
    return memberRepository.findById(memberId)
        .map(MemberDto::new)
        .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + memberId));
  }

  public Member getInfo(String memberId) {
    return memberRepository.findByMemberId(memberId);
  }

  public Member getCurrentUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      return memberRepository.findByMemberId(username);
    }

    return null;
  }

  public boolean matchesPassword(String rawPassword, String encodedPassword) {
    return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
  }


}
