package com.demo.security01.config.annotation;

import com.demo.security01.validator.test.TestAddrValidator;
import org.springframework.validation.annotation.Validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TestAddrValidator.class)
public @interface TestAddrAnno {

    String message() default  "사용할 수 없는 주소";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
