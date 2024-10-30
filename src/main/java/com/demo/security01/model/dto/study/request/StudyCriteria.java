package com.demo.security01.model.dto.study.request;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class StudyCriteria {

	private Long categoryIdx;
    private List<Long> skillIdx;
    private List<Long> positionIdx;
    private String process;
    private Integer isFin;



}
