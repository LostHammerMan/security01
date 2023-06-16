package com.demo.security01.config.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;
@Slf4j
public class PagingConversionService {

    @Autowired
    private ConversionService pagingConversion;

    public void doConvert(String perPageNum){
      /*  log.info("======== doConvert called..... =======");
        log.info("perPageNum = {}", perPageNum);
        Integer convertedPerPageNum = pagingConversion.convert(perPageNum, Integer.class);
//        pagingConversion.canConvert()
        log.info("convertedPerPageNum = {}", convertedPerPageNum);*/

        DefaultConversionService defaultConversionService = new DefaultConversionService();
        defaultConversionService.canConvert(String.class, Integer.class);
//        defaultConversionService.addConverter();
    }
}
