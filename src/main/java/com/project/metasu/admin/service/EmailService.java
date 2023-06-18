package com.project.metasu.admin.service;

import com.project.metasu.admin.domain.dto.MailDto;

public interface EmailService {
  public void sendRentalRemindEmail(MailDto mailDto);
}
