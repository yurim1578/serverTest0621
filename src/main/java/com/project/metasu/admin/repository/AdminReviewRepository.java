package com.project.metasu.admin.repository;

import com.project.metasu.item.domain.entity.Review;
import com.project.metasu.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminReviewRepository extends JpaRepository<Review, Integer> {
  int countByMemberId(Member memberId);
}
