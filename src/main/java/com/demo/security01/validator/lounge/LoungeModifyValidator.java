package com.demo.security01.validator.lounge;

import com.demo.security01.model.dto.community.LoungeModifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component(value = "loungeModifyValidator")
public class LoungeModifyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        log.info("LoungeModifyValidator - supports called....");
        return LoungeModifyRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("LoungeModifyValidator - validate...");
        LoungeModifyRequest request = (LoungeModifyRequest) target;
        checkCateCode(request, errors);
        checkTitle(request, errors);
        checkContents(request, errors);
    }

    public void checkCateCode(LoungeModifyRequest request, Errors errors){

        // null, typeMisMatch
        if (errors.hasFieldErrors("cateCode")){
            return;
        }

        if (request.getCateCode() != 5L && request.getCateCode() != 6L){
            errors.rejectValue("cateCode", "InvalidCode");
            return;
        }
    }

    public void checkTitle(LoungeModifyRequest request, Errors errors){
        log.info("LoungeModifyRequest checkTitle");
        log.info("title = {}", request.getTitle());
        if (!StringUtils.hasText(request.getTitle())){

            errors.rejectValue("title", "NotBlank");
            return;
        }
    }

    public void checkContents(LoungeModifyRequest request, Errors errors){
        log.info("LoungeModifyRequest checkContents");
        log.info("contents = {}", request.getContents());
        if (!StringUtils.hasText(request.getContents())){
            errors.rejectValue("contents", "NotBlank");
            return;
        }
    }
}
