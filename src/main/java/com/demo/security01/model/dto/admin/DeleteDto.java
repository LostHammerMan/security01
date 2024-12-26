package com.demo.security01.model.dto.admin;

import lombok.Data;

import java.util.List;

@Data
public class DeleteDto {

    List<Integer> id;
    private String text;
    private String text2;
}
