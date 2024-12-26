package com.demo.security01.model.dto.community;

import com.demo.security01.model.SortOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class LoungeCriteria {
	
	private Long cateCode;
	private String keyword;
	private SortOrder order;

}
