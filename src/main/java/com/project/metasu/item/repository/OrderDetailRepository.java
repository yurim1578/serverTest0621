package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.OrderDetail;
import com.project.metasu.item.domain.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
