package com.project.metasu;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityListeners;
@EnableScheduling
@EnableBatchProcessing
@EnableJpaRepositories
@EnableJpaAuditing // 이 어노테이션을 사용해야 자동으로 데이터 인서트됨
@EntityListeners(AuditingEntityListener.class) //@EnableJpaAuditing 어노테이션을 사용하기 위해서
@SpringBootApplication
public class MetasuApplication {

	public static void main(String[] args) {

		SpringApplication.run(MetasuApplication.class, args);
	}

}
