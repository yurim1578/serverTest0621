
package com.project.metasu.scheduler;

import com.project.metasu.item.domain.entity.Rental;
import com.project.metasu.item.repository.RentalRepository;
import com.project.metasu.item.service.ItemService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class JobAutoPay extends QuartzJobBean {
  private static final Logger log = LoggerFactory.getLogger(JobAutoPay.class);
  @Autowired
  private RentalRepository rentalRepository;
  @Autowired
  private ItemService itemService;

    @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    log.info("결제승인 배치 start");
    String today = "10";
    //String today = String.valueOf(LocalDate.now().getDayOfMonth());
    log.info("today : " + LocalDate.now().getDayOfMonth());

    if(today.equals("5") || today.equals("10") || today.equals("25")){

         List<Rental> rental = rentalRepository.findByRentalPayAutoDate(today); // 오늘 날짜가 rentalPayAutoDate인 렌탈 list들 find

        if (rental != null) {
          rental.stream().forEach(e -> {
            try {
                ResponseEntity status = itemService.acceptPayment(e.getRentalNo()); // 토스측에 결제 승인 요청 + 승인시 payment save

                    if (status.getStatusCode() == HttpStatus.OK) {  // 결제 승인이 성공적으로 이루어진 경우
                    log.info("결제 승인완료!");

                } else {  // 결제 승인이 실패했거나 다른 상태 코드일 경우
                    log.info("결제에 실패하셨습니다!");
                    itemService.notAcceptPayment(e.getRentalNo()); // 결제 실패시 update 로직
                }

            } catch (IOException ex) {
              throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
              throw new RuntimeException(ex);
            }
          });
        }else{
          log.info("승인할 결제건이 없습니다.");
        }

    }
   log.info("배치 end");
  }
}



