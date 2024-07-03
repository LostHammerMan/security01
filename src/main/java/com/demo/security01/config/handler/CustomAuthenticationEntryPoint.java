package com.demo.security01.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("==== CustomAuthenticationEntryPoint - commence ======");
        // 로그인 필요 alert, 로그인페이지 리다이렉트
        String redirectUrl = request.getContextPath() + "/user/loginForm";
        String redirectUrl2 = "/user/loginForm";
        log.info("request.getContextPath() = {}", request.getContextPath());
//        String script = "<script type=\"text/javascript\">"
//                + "alert('로그인이 필요합니다.);"
//                + "window.location.href='" + redirectUrl + "';"
//                + "</script>";

        String script = "<script type=\"text/javascript\">"
                + "alert('로그인이 필요합니다');"
                + "window.location.href='" + redirectUrl + "';"
                + "</script>";

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(script);
    }
}
