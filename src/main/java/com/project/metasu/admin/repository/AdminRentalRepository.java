package com.project.metasu.admin.repository;

import com.project.metasu.item.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface AdminRentalRepository extends JpaRepository<Payment,String> {
  @Query(value="select r.rental_no,r.rental_start_date,r.rental_end_date,r.rental_period,r.rental_pay_auto_date,r.rental_rental_pay_auto_yn, r.rental_status " +
      ",p.payment_no,p.payment_status,p.payment_amount,p.payment_type,p.payment_bank,p.payment_account,p.payment_credit_number " +
      "from rental r join order_master o on r.rental_no=o.rental_no join payment p on o.payment_no=p.payment_no " +
      "where r.rental_no=:rentalNo",nativeQuery = true)
  Map<String,Object> findRentalPayments(@Param("rentalNo") String rentalNo);

}
