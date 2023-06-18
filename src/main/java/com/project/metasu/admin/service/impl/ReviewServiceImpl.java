package com.project.metasu.admin.service.impl;

import com.project.metasu.item.repository.AdminReviewRepository;
import com.project.metasu.admin.service.ReviewService;
import com.project.metasu.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {
  private final AdminReviewRepository adminReviewRepository;
  @Override
  public int findReviewNumById(Member memberId){
    return adminReviewRepository.countByMemberId(memberId);
  }
}
