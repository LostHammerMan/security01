package com.demo.security01.config.converter;

import com.demo.security01.config.annotation.PagingAnno;
import com.demo.security01.model.dto.paging.Criteria;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class PagingConverter3 implements ConditionalGenericConverter {

    // Convert 하려는 sourceType, targetType 확인 - 1 순위 확인
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Integer.class));
    }

    // targetType이 @PagingAnno 있는 경우에 convert 진행 - 2순위
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return targetType.getAnnotation(PagingAnno.class) != null;
    }

    // convert 로직 - 3순위
    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        log.info(" =========== PagingPerPageNum called =============");

        List<String> perPageNumList = new ArrayList<>();
        Collections.addAll(perPageNumList, "10", "20", "30");

        String sourceValue = (String) source;

        if (!perPageNumList.contains(source)){
            return 10;
        }else {
            log.info("Converted_source = {}", Integer.parseInt(sourceValue));
            return Integer.parseInt(sourceValue);
        }
    }
}
