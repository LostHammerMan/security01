package com.demo.security01.config.exception;

import com.demo.security01.config.handler.GlobalException;
import lombok.Getter;

@Getter
public class LoungeNotFountException extends GlobalException {

    static final String MSG = "해당 게시글을 찾을 수 없습니다";

    public LoungeNotFountException() {
        super(MSG);
    }
}
