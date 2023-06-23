package com.project.metasu.scheduler.service;

import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;


public interface SchedulerService {

    // 반납요청(계약취소)를 확인하고 승인하는 메서드
    ResponseEntity updateReturnStatus(String contractStatus);

}
