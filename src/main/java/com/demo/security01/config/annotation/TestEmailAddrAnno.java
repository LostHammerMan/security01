package com.demo.security01.config.annotation;

import com.demo.security01.validator.test.TestEmailAddrValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TestEmailAddrValidator.class)
public @interface TestEmailAddrAnno {

    String message() default  "사용할 수 없는 이름";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

