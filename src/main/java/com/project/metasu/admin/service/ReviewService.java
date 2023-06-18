package com.project.metasu.admin.service;

import com.project.metasu.member.domain.entity.Member;

public interface ReviewService {
  public int findReviewNumById(Member memberId);
}
