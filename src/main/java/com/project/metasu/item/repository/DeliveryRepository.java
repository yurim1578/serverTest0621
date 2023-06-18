package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.Cart;
import com.project.metasu.item.domain.entity.CartId;
import com.project.metasu.item.domain.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DeliveryRepository extends JpaRepository<Delivery, String> {
    Boolean existsByInstallDateAndInstallTimeCode(LocalDate installDate, String installTimeCode);
}
