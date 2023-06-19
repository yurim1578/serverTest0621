package com.project.metasu.admin.service.impl;

import com.project.metasu.admin.service.OrderService;
import com.project.metasu.admin.repository.AdminOrderRepository;
import com.project.metasu.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
  private final AdminOrderRepository adminOrderRepository;

  @Override
  public int findOrderNumById(Member memberId){
    return adminOrderRepository.countByMemberId(memberId);
  }

  @Override
  public List<Map<String, Object>> getOrderLists() {
    return adminOrderRepository.getOderList();
  }

  @Override
  public Map<String, Object> getOrderDetail(String orderNo) {
    return adminOrderRepository.getOrderDetail(orderNo);
  }

  @Override
  public Map<String, Object> getContract(String contactNo) {
    return adminOrderRepository.findContract(contactNo);
  }
}
