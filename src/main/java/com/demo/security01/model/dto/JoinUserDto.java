package com.demo.security01.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class JoinUserDto {

    private String username;
    private String password;
    private String email;

}
