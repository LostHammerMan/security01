package com.demo.security01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터(SecurityConfig class)가 스프링 필터체인에 등록됨
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화 , prePostEnabled = true - preAuthorize, postAuthorize 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() // 인증만 되면 들어갈 수 있는 구조
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .anyRequest().permitAll() // 그 외 다른 요청은 모두에게 열려있음
                .and()
                .formLogin()
                .loginPage("/loginForm") // 로그인 페이지 화면
                .usernameParameter("username") // default : username, 다른 파라미터로 바꾸려는 경우 해당 파라미터 이름으로 작성
                .loginProcessingUrl("/login") // login 주소 호출 -> 시큐리티가 낚아채서 대신 로그인 진행 --> controller 에 login 만들지 않아도 됨
                .defaultSuccessUrl("/");

    }

    @Bean // 리턴되는 오브젝트를 IoC 로 등록해줌
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
