package com.project.metasu.member.service;

import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.repository.ItemMasterRepository;
import com.project.metasu.member.domain.entity.Member;

import java.util.List;
//ItemMasterRepository 에서 호출
public class ItemMasterService {

  ItemMasterRepository itemMasterRepository;
  public List<ItemMaster> findItemsByMember(Member member) {
    return itemMasterRepository.findByMember(member);
  }

}
