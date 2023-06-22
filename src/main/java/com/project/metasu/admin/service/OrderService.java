package com.project.metasu.admin.service;

import com.project.metasu.member.domain.entity.Member;

import java.util.List;
import java.util.Map;

public interface OrderService {
  int findOrderNumById(String memberId);
  List<Map<String,Object>> getOrderLists();

  List<Map<String,Object>> getOrderDetail(String orderNo);
  Map<String,Object> getContract(String contactNo);
}
