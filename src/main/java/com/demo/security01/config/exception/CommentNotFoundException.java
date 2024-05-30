package com.demo.security01.config.exception;

import com.demo.security01.config.handler.GlobalException;

public class CommentNotFoundException extends GlobalException {

    static final String MSG = "해당 댓글을 찾을 수 없습니다";

    public CommentNotFoundException() {
        super(MSG);
    }
}
