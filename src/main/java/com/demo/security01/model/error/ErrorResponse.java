package com.demo.security01.model.error;

import lombok.Data;

@Data
public class ErrorResponse {
    int status;
    String message;
}
