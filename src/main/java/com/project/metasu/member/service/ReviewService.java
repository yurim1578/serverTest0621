package com.project.metasu.member.service;

import com.project.metasu.item.domain.entity.Review;
import com.project.metasu.item.dto.ReviewDTO;
import com.project.metasu.item.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// ReviewService 클래스의 일부입니다.
@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  // ...

  public Review saveReview(ReviewDTO reviewDTO) {
    Review review = new Review(reviewDTO);
    return reviewRepository.save(review);
  }
}




