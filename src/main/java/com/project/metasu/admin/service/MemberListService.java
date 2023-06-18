package com.project.metasu.admin.service;

import com.project.metasu.member.domain.entity.Member;

import java.util.List;

public interface MemberListService {
  List<Member> findAllMembers(String memberAuth);
  Member findByMemberId(String memberId);
}
