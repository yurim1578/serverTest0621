package com.project.metasu.admin.service.impl;

import com.project.metasu.admin.domain.dto.MailDto;
import com.project.metasu.admin.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailServiceImpl implements EmailService {
  private JavaMailSender emailSender;
  @Override
 // @Scheduled(cron = "0 0 9 L * ?")  //초_분_시_일(L-말일) //납부일 1일전 9시 전송 가능한가...?!!!!!
  public void sendRentalRemindEmail(MailDto mailDto) {
    SimpleMailMessage message=new SimpleMailMessage();
    message.setTo(mailDto.getEmail());
    message.setSubject("[청구] 이번 달 METASU 정수기 렌탈비가 납부일이 도래하였습니다.");
    message.setText(mailDto.getName()+"님, 안녕하세요.\n계약번호"+mailDto.getContractNo()+
        "번의 이번 달 렌탈비 납부일("+mailDto.getRentalDate()+")이 도래하여 알려드립니다.\n"
        +mailDto.getPaymentAmount()+"원 입금부탁드립니다.\n정수기 렌탈 만료일은"+
        mailDto.getRentalEndDate()+"입니다.");
  }
}
