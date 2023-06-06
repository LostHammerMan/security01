package com.demo.security01.config.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class PagingConverter3 implements ConditionalGenericConverter {

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return targetType.equals(Integer.class) && isPerPageNumField(Integer.class);
    }

    private boolean isPerPageNumField(Class<?> fieldType){
        return fieldType.equals(Integer.class);
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Integer.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        log.info("## {}", this.getClass().getSimpleName());
        log.info("\t > source = {}", source);
        String source2 = (String) source;

        List<String> perPageNumList = new ArrayList<>();
        Collections.addAll(perPageNumList, "10", "20", "30");


        if (!perPageNumList.contains(source2)){
            return 10;
        }else {
            log.info("Converted_source = {}", Integer.parseInt(source2));
            return Integer.parseInt(source2);
        }
    }
}
