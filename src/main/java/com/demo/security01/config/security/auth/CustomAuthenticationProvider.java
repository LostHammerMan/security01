package com.demo.security01.config.security.auth;

import com.demo.security01.config.exception.LoginFieldException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.MessageUtil;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
//@Component // 타입으로 빈 지정
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final PrincipalDetailService principalDetailService;
    private final PasswordEncoder encoder;
    private final MessageSourceAccessor messageSourceAccessor;

    //
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("============ authenticate called =============");
        log.info("========== {} ===========", this.getClass());

        String username = (String) authentication.getName();
//        String username2 = authentication.getName();
        String password = (String) authentication.getCredentials();
//        String authority = authentication.getAuthorities().toString();

        log.info("username = {}", username);
//        log.info("username2 = {}", username2);
        log.info("password = {}", password);


        // DB 에 사용자 정보 조회하기 전 처리
        // 빈값이 입력되는 경우
        if (username.equals("")){
            throw new LoginFieldException(messageSourceAccessor.getMessage("error.loginRequired.username"));
        } else if (password.equals("")) {
            throw new LoginFieldException(messageSourceAccessor.getMessage("error.loginRequired.password"));
        }

        // username 을 가지고 사용자 정보 조회
        UserDetails loginUser = principalDetailService.loadUserByUsername(username);
        
        // 조회 후 password 가 일치하지 않는 경우
        if (!this.encoder.matches(password, loginUser.getPassword())){
            throw new BadCredentialsException(messageSourceAccessor.getMessage("error.BadCredential.password"));
        }
        String authority = loginUser.getAuthorities().toString();
        log.info("authority = {}", authority);
        return new UsernamePasswordAuthenticationToken(loginUser, password, loginUser.getAuthorities());
    }


}
