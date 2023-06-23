package com.project.metasu.scheduler.service.impl;

import com.project.metasu.item.domain.entity.Contract;
import com.project.metasu.item.domain.entity.Rental;
import com.project.metasu.item.repository.ContractRepository;
import com.project.metasu.item.repository.RentalRepository;
import com.project.metasu.item.service.ItemService;
import com.project.metasu.scheduler.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final RentalRepository rentalRepository;
    private final ContractRepository contractRepository;
    private final ItemService itemService;

    // 반납요청(계약취소)를 확인하고 승인하는 메서드
    @Override
    @Transactional
    public ResponseEntity updateReturnStatus(String contractStatus) {
        List<Contract> lists = contractRepository.findByContractStatus(contractStatus);
        lists.stream().forEach(contract -> {
                    contract.setContractStatus("CA"); //CANCEL ACCEPT
                    contractRepository.save(contract);
                });
        return ResponseEntity.ok(HttpStatus.OK.value());
    }

}
