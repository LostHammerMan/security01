package com.demo.security01.config.exception;

import com.demo.security01.config.handler.GlobalException;

public class UserNotMatchException extends GlobalException {

    private static final String MSG = "유저가 일치하지 않습니다";

    public UserNotMatchException() {
        super(MSG);
    }
}
