package com.demo.security01.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Response {
    private int status;         // exception
    private String message;     // bindingResult
    private LocalDateTime timestamp = LocalDateTime.now();

//    private Response() { }
//
//    public static Response builder() {
//        return new Response();
//    }
//
//    public Response status(int status) {
//        this.status = status;
//        return this;
//    }
//
//    public Response message(String message) {
//        this.message = message;
//        return this;
//    }
}
