package com.demo.security01.config.converter;

import com.demo.security01.config.annotation.PagingAnno;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
//@Component
public class PagingTypeConverter implements ConditionalGenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, String.class));
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return sourceType.getAnnotation(PagingAnno.class) != null;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        log.info(" =========== PagingTypeConverter called =============");

        String sourceValue = (String) source;
        log.info("sourceValue = {}", source);
        log.info("sourceValue = {}", sourceValue);
        List<String> pagingTypeList = new ArrayList<>();
        Collections.addAll(pagingTypeList, "U", "E", "R");

        if (!pagingTypeList.contains(source)){
            return "";
        }
        return sourceValue;

    }
}
