package com.project.metasu.member.repository;

import com.project.metasu.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminMemberRepository extends JpaRepository<Member, String> {
  List<Member> findAllByMemberAuth(String memberAuth);

  Member findOneByMemberId(String memberId);
}
