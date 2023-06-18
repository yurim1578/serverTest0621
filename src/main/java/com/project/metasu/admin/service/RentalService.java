package com.project.metasu.admin.service;

import com.project.metasu.item.domain.entity.Rental;

import java.util.List;
import java.util.Map;

public interface RentalService {
  public Map<String, Object> getRentalNPayment(String rentalNo);

}
