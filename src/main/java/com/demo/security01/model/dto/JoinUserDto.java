package com.demo.security01.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class JoinUserDto {

    private String username;
    private String password;
    private String email_id;
    private String email_domain;
    private String email_etc;
    private String email_addr;
    private String auth_code;
//    private boolean email_authenticated;


}
