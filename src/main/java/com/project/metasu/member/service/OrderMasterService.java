package com.project.metasu.member.service;

import com.project.metasu.item.domain.entity.OrderMaster;
import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.repository.OrderMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMasterService {

  @Autowired
  private OrderMasterRepository orderMasterRepository;

  public List<OrderMaster> getOrdersByMember(Member memberId) {
    return orderMasterRepository.findByMemberId(memberId);
  }
}
