package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.Contract;
import com.project.metasu.item.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
