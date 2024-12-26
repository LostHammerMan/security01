package com.demo.security01.model.dto.crawling;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudyCrawlingResponeDto {

	private String title;
	private String content;
	private String link;
	
	@Builder
	public StudyCrawlingResponeDto(String title, String content,String link) {
		this.title = title;
		this.content = content;
		this.link = link;
	}
	
	
}
