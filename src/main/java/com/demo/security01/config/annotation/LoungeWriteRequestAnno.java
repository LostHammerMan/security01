package com.demo.security01.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 파라미터에 쓰일 어노테이션임을 명시
@Retention(RetentionPolicy.RUNTIME)
public @interface LoungeWriteRequestAnno {
}
