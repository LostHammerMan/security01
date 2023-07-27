package com.demo.security01.model.dto.reponseDto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseEntityDto {

    private int status;
    private String message;
    private Object objectData;
    private LocalDateTime localDateTime;
}
