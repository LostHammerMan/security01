package com.demo.security01.config.handler;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Provider;

//@Data
@Slf4j
@Configuration
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private String loginUsername;
    private String loginPassword;
    private String errorMessageName;
    String defaultFailureUrl;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("============ loginFailureHandler called.. =================");
        String loginUsername = request.getParameter("username");
        log.info("loginUsername = {}", loginUsername);
        String loginPassword = request.getParameter("password");
        log.info("loginPassword = {}", loginPassword);

        String errorMsg = null;
        defaultFailureUrl = "/loginForm";

        // 빈값을 입력하는 경우
        if (exception instanceof UsernameNotFoundException) {
            errorMsg = "1"; // 비밀번호가 일치하지 않음
        } else if (exception instanceof BadCredentialsException) {
            errorMsg = "2"; // 계정이 존재하지 않음
        } else if (exception instanceof SessionAuthenticationException) {
            errorMsg = "3"; // 중복 계정
        }

        log.info("errorMsg = {}", errorMsg);
        log.info("defaultFailureUrl = {}", defaultFailureUrl);

        request.setAttribute("loginPassword", loginUsername);
        request.setAttribute("loginPassword", loginPassword);
        request.setAttribute("errorMsg", errorMsg);

        request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
       /* assert errorMsg != null;
        response.sendRedirect(defaultFailureUrl + URLEncoder.encode(errorMsg, StandardCharsets.UTF_8));*/
    }

}
