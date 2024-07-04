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

        // 첫 요청이 NPM 이 발생함
//        boolean isAjaxRequest = request.getHeader("X-Requested-With").equals("XMLHttpRequest");
/*
* <NPM 발생 타이밍>
  위 구문에서는 request.getHeader() 호출 시점에 null 이 발생, boolean isAjaxRequest = null 이 되는것
* 아래 구문에서는 문자열.eqaul() 이므로 request.getHeader() 가 null 이 더라도 false 가 반환됨
* 즉, 늘 null-safe 한 코드를 짜기 위해 노력해야 함
* */
        // 첫 요청이 NPM 이 발생하지 않음

        boolean isAjaxRequest = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        if (isAjaxRequest){
            // Ajax 요청인 경우 로그인 필요한 경우
            log.info("request.getHeader('X-Requested-With') = {}", request.getHeader("X-Requested-With") );
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"message\": \"로그인이 필요합니다.\"}");

        }else {
            // ajax 요청이 아닌 경우
            // 로그인 필요 alert, 로그인페이지 리다이렉트
            String redirectUrl = request.getContextPath() + "/user/loginForm";
            String redirectUrl2 = "/user/loginForm";

            String script = "<script type=\"text/javascript\">"
                    + "alert('로그인이 필요합니다');"
                    + "window.location.href='" + redirectUrl + "';"
                    + "</script>";

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(script);
        }

    }
}
