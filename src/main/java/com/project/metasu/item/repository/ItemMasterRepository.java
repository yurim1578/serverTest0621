package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemMasterRepository extends JpaRepository<ItemMaster, String> {
  // 사용자 ID를 기반으로 항목 세부 정보를 가져오는 메서드->ItemMasterService에서 해당 메소드를 호출
  List<ItemMaster> findByMember(Member member);

}
