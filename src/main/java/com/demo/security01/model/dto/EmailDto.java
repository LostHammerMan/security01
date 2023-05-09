package com.demo.security01.model.dto;

import lombok.Data;

@Data
public class EmailDto {

    private String email_id;
    private String email_domain;
    private String email_addr;
    private boolean email_authenticated;
}
