package com.demo.security01.config.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
//@Component
public class PagingConverter2 implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        log.info("## {}", this.getClass().getSimpleName());
        log.info("\t > source = {}", source);

//        int perPageNum = Integer.parseInt(source);

        // List<
        List<String> perPageNumList = new ArrayList<>();
        Collections.addAll(perPageNumList, "10", "20", "30");

        if (!perPageNumList.contains(source)){
            return 10;
        }else {
            log.info("Converted_source = {}", Integer.parseInt(source));
            return Integer.parseInt(source);
        }

//        // perPageNum 변경시 확인 로직
//        List<Integer> perPageNumList = new ArrayList<>();
//        Collections.addAll(perPageNumList, 10, 20, 30);
//
//        try {
//            if (perPageNumList.contains(perPageNum)){
//                this.perPageNum = perPageNum;
//            }else {
//                this.perPageNum = 10;
//            }
//        }catch (Exception ex){
//            this.perPageNum = 10;
//        }
//

        /*try {
            return Integer.valueOf(source);
        } catch (Exception e) {
            log.info("\t > {}", e.getClass().getSimpleName());
            return 40;
        }*/
    }
}
