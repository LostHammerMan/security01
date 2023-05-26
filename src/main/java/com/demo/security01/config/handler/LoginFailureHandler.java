package com.demo.security01.config.handler;


import com.demo.security01.config.exception.LoginFieldException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
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

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    String defaultFailureUrl;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("============ loginFailureHandler called.. =================");
        String loginUsername = request.getParameter("username");
        String loginPassword = request.getParameter("password");
        String errorMsg = null;

        defaultFailureUrl = "/user/loginForm";

        // exception 에 따른 메시지 출력
        if (exception instanceof LoginFieldException){
            errorMsg = exception.getMessage();
        }else if (exception instanceof UsernameNotFoundException){
            errorMsg = exception.getMessage();
        }else if (exception instanceof BadCredentialsException){
            errorMsg = exception.getMessage();
        }
        log.info("errorMsg = {}", errorMsg);
        log.info("defaultFailureUrl = {}", defaultFailureUrl);

        /*request.setAttribute("loginPassword", loginUsername);
        request.setAttribute("loginPassword", loginPassword);*/
        request.setAttribute("errorMsg", errorMsg);
        request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
       /* assert errorMsg != null;
        response.sendRedirect(defaultFailureUrl + URLEncoder.encode(errorMsg, StandardCharsets.UTF_8));*/
    }

}
