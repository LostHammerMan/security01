package com.demo.security01.config.converter;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;

public class PagingConversionService /*implements ConversionService*/ {

    /*@Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        return false;
    }

    @Override
    public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return false;
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        return null;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return null;
    }

    DefaultConversionService pagingConversionService = new DefaultConversionService();

    @Bean
    public ConversionService pagingConversionService(){
        //pagingConversionService.addConverter(new PagingConverter());
//        pagingConversionService.canConvert() // 변환 여부 확인하는 메서드
        pagingConversionService.addConverter(new PagingConverter2());
        return pagingConversionService;
    }*/
}
