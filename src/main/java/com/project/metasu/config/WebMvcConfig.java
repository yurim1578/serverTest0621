package com.project.metasu.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity   // 스프링 시큐리티 필터가 스프링 필터 체인에 등록됨
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)   // "@Secured", "@PreAuthorize" 애너테이션 활성화
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    MustacheViewResolver resolver = new MustacheViewResolver();

    resolver.setCharset("UTF-8");
    resolver.setContentType("text/html;charset=UTF-8");
    resolver.setPrefix("classpath:/templates/");
    resolver.setSuffix(".html");

    registry.viewResolver(resolver);
  }

}

// .html 파일을 .mustache 파일로 인식이 가능하도록 설정