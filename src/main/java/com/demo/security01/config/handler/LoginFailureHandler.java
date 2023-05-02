package com.demo.security01.config.handler;


import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private String loginUsername;
    private String loginPassword;
    private String msg;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter(loginUsername);
        String password = request.getParameter(loginPassword);

        request.setAttribute(loginUsername, username);
        request.setAttribute(loginPassword, password);

    }

}
