package com.project.metasu.member.repository;

import com.project.metasu.item.domain.entity.OrderMaster;
import com.project.metasu.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, Long> {
  List<OrderMaster> findByMemberId(Member memberId);
}
