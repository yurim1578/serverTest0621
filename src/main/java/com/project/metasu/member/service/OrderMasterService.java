package com.project.metasu.member.service;

import com.project.metasu.item.domain.entity.OrderMaster;
import com.project.metasu.item.repository.OrderMasterRepository;
import com.project.metasu.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderMasterService {

  private final OrderMasterRepository orderMasterRepository;

  public List<OrderMaster> getOrdersByMember(Member memberId) {
    return orderMasterRepository.findByMemberId(memberId.getMemberId());
  }
}
