package com.demo.security01.config.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class LoginFieldException extends AuthenticationException {

    public LoginFieldException(String msg) {
        super(msg);
    }

}
