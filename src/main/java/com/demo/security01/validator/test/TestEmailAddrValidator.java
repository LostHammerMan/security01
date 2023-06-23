package com.demo.security01.validator.test;

import com.demo.security01.config.annotation.TestEmailAddrAnno;
import com.demo.security01.model.dto.test.TestValidatorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
//@Component(value = "testEmailAddrValidator")
public class TestEmailAddrValidator implements ConstraintValidator<TestEmailAddrAnno, String> {

    @Override
    public void initialize(TestEmailAddrAnno constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.info("isValid called....");
        log.info("testEmailAddr = {}", value);
        if (!StringUtils.hasText(value)){
            context.buildConstraintViolationWithTemplate("이름입력해주세요")
                    .addConstraintViolation();
            return false;

        }

        return "Lee".equals(value);
    }
}
