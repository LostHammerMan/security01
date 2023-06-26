package com.demo.security01.model.dto.error;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponseDto {
    int status;
    String message;
    LocalDateTime localDateTime;
}
