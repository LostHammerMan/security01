package com.demo.security01.model.dto.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDto {

	private int page = 1;
	private int size = 12;
}
