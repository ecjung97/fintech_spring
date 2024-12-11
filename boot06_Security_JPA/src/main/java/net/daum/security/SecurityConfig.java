package net.daum.security;


import lombok.extern.java.Log;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Log
@EnableWebSecurity // 스프링 웹 시큐리티로 인식되게 @EnableWebSecurity 애너테이션을 추가
public class SecurityConfig extends WebSecurityConfigurerAdapter {@Override
    // 스프링 웹 시큐리티 설정을 담당하는 WebSecurityConfigureAdapter 클래스 상속을 받는다.

    protected void configure(HttpSecurity http) throws Exception {
        // HttpSecurity는 웹과 관련된 다양한 보안설정을 걸어 줄 수 있다.

        log.info("security config ......");
    }
}
