package com.demo.security01.model.dto.study.request;

import java.util.List;

import lombok.Data;

@Data
public class StudyCriteria {

	private Long categoryIdx;
    private List<Long> skillIdx;
    private List<Long> positionIdx;
    private String process;
    private Integer isFin;
    private String keyword;

}
