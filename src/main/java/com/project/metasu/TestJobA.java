package com.project.metasu;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class TestJobA extends QuartzJobBean {
  private static final Logger log= LoggerFactory.getLogger(TestJobA.class);

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    //log.info("10초마다 JobA 실행");
  }
}
