package com.project.metasu.admin.controller;

import com.project.metasu.admin.domain.dto.MailDto;
import com.project.metasu.admin.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {
  //페이지 처리 안할건데 controller필요한가????
  //구글계정-보안-보안수준이 낮은 앱의 액세스 허용

  private final EmailService emailService;

  public EmailController(EmailService emailService) {
    this.emailService = emailService;
  }

  @PostMapping
  public String sendMail(MailDto mailDto) {
    emailService.sendRentalRemindEmail(mailDto);
    System.out.println("메일 전송 완료");
    return "";
  }
}
