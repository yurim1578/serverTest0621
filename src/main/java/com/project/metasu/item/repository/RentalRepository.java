package com.project.metasu.item.repository;
import com.project.metasu.item.domain.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface RentalRepository extends JpaRepository<Rental, String> {
    @Query("SELECT r FROM Rental r WHERE r.RentalPayAutoDate = :rentalPayAutoDate")
    List<Rental> findByRentalPayAutoDate(@Param("rentalPayAutoDate") String rentalPayAutoDate);

  /*  @Query("SELECT r FROM Rental r WHERE r.rentalEndDate >= :startDate AND r.rentalEndDate < :endDate")
    List<Rental> findByRentalEndDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);*/
}
