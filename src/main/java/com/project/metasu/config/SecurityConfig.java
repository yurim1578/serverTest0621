package com.project.metasu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity   // 스프링 시큐리티 필터가 스프링 필터 체인에 등록됨
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/member/register").permitAll()
        .antMatchers("/member/login").permitAll()
        .antMatchers("/member/mypage/**").authenticated()
        .antMatchers("/member/update/confirm").authenticated()
        //어드민 추가
        //.antMatchers("/admin/**").authenticated()
        //상품 쪽 권한 제한 추가
        //.antMatchers("/item/cart/**").authenticated()
        //.antMatchers("/item/salesOrder/**").authenticated()
        //.antMatchers("/item/rentalOrder/**").authenticated()
        .and()
        .formLogin()
        .loginPage("/member/login")
        .defaultSuccessUrl("/home/main")  // 성공 시 이동
        .failureUrl("/member/error")  // 실패 시 해당 URL로 이동
        .and()
        .logout()
        .logoutUrl("/member/logout")
        .logoutSuccessUrl("/member/login")
        .and()
        .csrf().disable();
  }
  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}