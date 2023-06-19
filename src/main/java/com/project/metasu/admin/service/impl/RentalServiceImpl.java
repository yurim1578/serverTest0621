package com.project.metasu.admin.service.impl;

import com.project.metasu.admin.service.RentalService;
import com.project.metasu.admin.repository.AdminRentalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RentalServiceImpl implements RentalService {
  private final AdminRentalRepository rentalRepository;

  @Override
  public Map<String, Object> getRentalNPayment(String rentalNo){
    return rentalRepository.findRentalPayments(rentalNo);
  }

}
