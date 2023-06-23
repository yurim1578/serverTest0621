package com.project.metasu.member.repository;

import com.project.metasu.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// @Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findById(String id);

    //authenticate클래스 의 메서드에 MemberController인증 논리
    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findByMemberIdAndMemberEmail(String memberId, String email);

    Optional<Member> findByMemberNameAndMemberEmail(String name, String email);

    Optional<Member> findMemberByMemberNameAndMemberEmail(String name, String email);

    Optional<Member> findMemberByMemberIdAndMemberEmail(String id, String email);
}

