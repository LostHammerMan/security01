package com.demo.security01.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.security01.config.handler.CustomFailureHandler;
import com.demo.security01.config.handler.LoginFailureHandler;
import com.demo.security01.config.security.auth.CustomAuthenticationProvider;
import com.demo.security01.config.security.auth.PrincipalDetailService;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터(SecurityConfig class)가 스프링 필터체인에 등록됨
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
// secured 어노테이션 활성화 , prePostEnabled = true - preAuthorize, postAuthorize 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

//    @Autowired
//    private AuthenticationProvider customAuthenticationProvider;

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

   /* @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;*/

    // username, password 기반 인증을 수행하는 Custom Provider 를 AuthenticationManager 에 등록
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
//                .ignoringAntMatchers("/login")
//                .ignoringAntMatchers("/loginForm")
                .disable();
        http.authorizeRequests()
                .antMatchers("/user/loginForm", "/user/joinForm", "/user/joinProc", "/user/resetPw","/community/lounge", "/favicon.ico", "/api/loungeList", "/api/study/**","/study").permitAll()
                .antMatchers("/user/**", "/community/lounge/**", "/api/study/like", "/study/**").authenticated() // 인증만 되면 들어갈 수 있는 구조(로그인 필요)
//                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .anyRequest().permitAll() // 그 외 다른 요청은 모두에게 열려있음
                .and()
                .formLogin()
                .loginPage("/user/loginForm") // 로그인 페이지 화면
                .usernameParameter("username") // default : username, 다른 파라미터로 바꾸려는 경우 해당 파라미터 이름으로 작성
                .passwordParameter("password")
                .loginProcessingUrl("/login") // login 주소 호출 -> 시큐리티가 낚아채서 대신 로그인 진행 --> controller 에 login 만들지 않아도 됨
                .defaultSuccessUrl("/")
//                .successHandler()
                .failureHandler(loginFailureHandler)
//                .failureForwardUrl("/loginForm?error")
                .and()
                //
                .exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .logout()
                .logoutUrl("/logout");
//                .invalidateHttpSession(true);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
//                .antMatchers("/css/**", "/js/**", "/img/**", "/favicon.ico")
//                .mvcMatchers("/node_modules/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean // 리턴되는 오브젝트를 IoC 로 등록해줌
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // CustomAuthenticationProvider 빈 등록
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(principalDetailService, encoder(), messageSourceAccessor);
    }

    @Bean
    public CustomFailureHandler customFailureHandlerBean() throws Exception {
        return new CustomFailureHandler();
    }

    /*@Bean
    public AuthenticationManager authenticationManagerManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
*/
//    @Bean
//    public LoginFailureHandler loginFailureHandlerBean(){
//        return new LoginFailureHandler();
//    }


}
