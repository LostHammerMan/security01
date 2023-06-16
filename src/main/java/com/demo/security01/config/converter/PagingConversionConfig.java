package com.demo.security01.config.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class PagingConversionConfig {

    @Bean
    public ConversionService pagingConversion(){
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new PagingConverter2());

        return conversionService;
    }
}
