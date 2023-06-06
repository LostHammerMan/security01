package com.demo.security01.config.converter;

import com.demo.security01.model.dto.paging.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// 스프링 부트 - @Component 으로 빈 등록 후 사용
// 스프링 레거시 - Web Config 에 등록 후 사용
@Slf4j
public class PagingConverter implements Converter<String, Criteria> {

    // Converter<Source, Target>
    // String -> Criteria
    @Override
    public Criteria convert(String source) {
        log.info("=========== Converter called ============");

        Criteria cri = new Criteria();
        log.info("source = {}", source);
        //  source = 10;page=2
        try {
            String[] params = source.split(";");
            // params = {"10", "page=2"}
            for (String param : params){ // 1
                log.info("params ={}", params);
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                log.info("key = {}", key);
                log.info("value = {}", value);

                if (key.equals("perPageNum")){
                    cri.setPerPageNum(Integer.parseInt(value));
                }

            }
        }catch (Exception e){
            log.info("## {}", e.getClass().getSimpleName());
            cri.setPage(1);
            cri.setPerPageNum(10);
        }

        log.info("converter cri = {}", cri);

        return cri;
    }

    /*@Override
    public Integer convert(String source) {

        return null;
    }*/
}
