package com.project.metasu.item.repository;
import com.project.metasu.item.domain.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RentalRepository extends JpaRepository<Rental, String> {
    @Query("SELECT r FROM Rental r WHERE r.RentalPayAutoDate = :rentalPayAutoDate")
    List<Rental> findByRentalPayAutoDate(@Param("rentalPayAutoDate") String rentalPayAutoDate);
}
