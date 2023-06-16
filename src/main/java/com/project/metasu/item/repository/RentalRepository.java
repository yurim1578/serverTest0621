package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.Contract;
import com.project.metasu.item.domain.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RentalRepository extends JpaRepository<Rental, String> {
}
