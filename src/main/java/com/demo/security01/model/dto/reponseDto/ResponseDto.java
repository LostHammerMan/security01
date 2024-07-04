package com.demo.security01.model.dto.reponseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseDto {

    private int status;
    private String message;
    private LocalDateTime timeStamp;

    @Builder
    public ResponseDto(int status, String message, LocalDateTime timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
