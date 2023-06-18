package com.project.metasu.admin.service.impl;

import com.project.metasu.admin.service.MemberListService;
import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.repository.AdminMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberListServiceImpl implements MemberListService {
  private final AdminMemberRepository adminMemberRepository;
  // 멤버 리스트
  @Override
  public List<Member> findAllMembers(String memberAuth){
    return adminMemberRepository.findAllByMemberAuth(memberAuth);
  }

  @Override
  public Member findByMemberId(String memberId){
    return adminMemberRepository.findOneByMemberId(memberId);
  }

}
