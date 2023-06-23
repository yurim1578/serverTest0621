
package com.project.metasu.scheduler;

import com.project.metasu.item.domain.entity.Rental;
import com.project.metasu.item.repository.ContractRepository;
import com.project.metasu.item.repository.RentalRepository;
import com.project.metasu.item.service.ItemService;
import com.project.metasu.scheduler.service.SchedulerService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.List;

@Component
public class JobReturnItem extends QuartzJobBean {
  private static final Logger log = LoggerFactory.getLogger(JobReturnItem.class);
  @Autowired
  private RentalRepository rentalRepository;
  @Autowired
  private ContractRepository contractRepository;
  @Autowired
  private ItemService itemService;
  @Autowired
  private SchedulerService schedulerService;

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    log.info("반납요청 배치 start");
    String contractStatus = "CI";
    ResponseEntity status = schedulerService.updateReturnStatus(contractStatus);

    if (status.getStatusCode() == HttpStatus.OK) {
        log.info("반납요청 승인완료");
    }else{
        log.info("반납요청 승인실패");
    }

    log.info("배치 end");
  }
}



