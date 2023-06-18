package com.project.metasu.member.repository;

import com.project.metasu.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminMemberRepository extends JpaRepository<Member, String> {


}
