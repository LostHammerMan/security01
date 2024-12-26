package com.demo.security01.config.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.demo.security01.model.BoardType;

// @Component 사용해 converter 등록한 경우, 자동으로 등록됨
@Component
public class StringToBoardTypeConverter implements Converter<String, BoardType>{

	@Override
	public BoardType convert(String value) {
		// TODO Auto-generated method stub
		
		return BoardType.valueOf(value.toUpperCase());
	}


	
}
