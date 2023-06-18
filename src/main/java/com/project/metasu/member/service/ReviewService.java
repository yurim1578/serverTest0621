package com.project.metasu.member.service;

import com.project.metasu.item.domain.entity.Review;
import com.project.metasu.member.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// ReviewService 클래스의 일부입니다.
@Service
public class ReviewService {
  @Autowired
  private final ReviewRepository reviewRepository;

  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  // ...

  public Review saveReview(com.project.metasu.item.dto.ReviewDTO reviewDTO) {
    Review review = new Review(reviewDTO);
    return reviewRepository.save(review);
  }
}




