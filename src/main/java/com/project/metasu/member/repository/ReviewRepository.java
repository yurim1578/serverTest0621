package com.project.metasu.member.repository;

import com.project.metasu.item.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository  extends JpaRepository<Review, Long> {

}
