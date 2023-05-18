package com.demo.security01.model.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

 // 404 잘못된 인증번호 요청
    BAD_AUTH_REQUEST(HttpStatus.BAD_REQUEST, "인증번호가 일치하지 않아요 ㅠㅠ");



    private final HttpStatus status;
    private final String message;
}
