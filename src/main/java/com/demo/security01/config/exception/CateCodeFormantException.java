package com.demo.security01.config.exception;

import com.demo.security01.config.handler.GlobalException;

public class CateCodeFormantException extends NumberFormatException {

    private static final String MSG = "카테고리를 선택해주세요";
    public CateCodeFormantException() {
        super(MSG);
    }
}
