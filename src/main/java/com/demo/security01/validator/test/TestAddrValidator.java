package com.demo.security01.validator.test;

import com.demo.security01.config.annotation.TestAddrAnno;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class TestAddrValidator implements ConstraintValidator<TestAddrAnno, String> {

    private boolean

    @Override
    public void initialize(TestAddrAnno constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

      log.info("TestAddrValidator called,.........");
        if (!StringUtils.hasText(value)){
            context.buildConstraintViolationWithTemplate("주소 입력해주세요")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }


}
