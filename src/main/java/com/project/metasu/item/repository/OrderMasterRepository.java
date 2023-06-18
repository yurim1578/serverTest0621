package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.OrderMaster;
import com.project.metasu.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    // 20230618
    List<OrderMaster> findByMemberId(String memberId);
}
