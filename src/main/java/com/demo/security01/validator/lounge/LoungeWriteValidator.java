package com.demo.security01.validator.lounge;

import com.demo.security01.model.dto.community.LoungeWriteRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TypeMismatchException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component(value = "loungeWriteValidator")
public class LoungeWriteValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LoungeWriteRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("======== LoungeWriteValidator called ==============");
        LoungeWriteRequest loungeWriteRequest = (LoungeWriteRequest) target;
        log.info("\t\t loungeWriteRequest = {}", loungeWriteRequest);

        checkCateCode(loungeWriteRequest, errors);
        checkTitle(loungeWriteRequest, errors);
        checkContents(loungeWriteRequest, errors);
    }

    public void checkCateCode(LoungeWriteRequest request, Errors errors){
        log.info("\t\t checkCateCode called...");
        log.info("\t\t\t request.cateCode = {}", request.getCateCode());

        if (errors.hasFieldErrors("cateCode")) { // typeMismatch, NotBlank
            return;
        }
//        if (request.getCateCode() == null){
//            errors.rejectValue("cateCode", "NotBlank");
//            return;
//        }

        // cateCode를 빈값을 보내거나 string을 보내면 LoungeWriteRequest(cateCode=null, title=, contents=)
        // null != 5L --> NPE

        // 카테고리 코드 정확하지 여부
        if (request.getCateCode() != 3L && request.getCateCode() != 4L){
            errors.rejectValue("cateCode", "invalidCateCode");
            return;
        }
//        if (request.getCateCode() != 5L || request.getCateCode() != 6L){
//            errors.rejectValue("cateCode", "invalidCateCode");
//            return;
//        }

    }

    public void checkTitle(LoungeWriteRequest request, Errors errors){
        log.info("\t\t checkTitle called...");
        if (!StringUtils.hasText(request.getTitle())){
            errors.rejectValue("title", "NotBlank");
            return;
        }
    }

    public void checkContents(LoungeWriteRequest request, Errors errors){
        if (!StringUtils.hasText(request.getContents())){
            errors.rejectValue("contents", "NotBlank");
        }
    }
}
