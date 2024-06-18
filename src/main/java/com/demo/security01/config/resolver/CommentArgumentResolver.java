package com.demo.security01.config.resolver;

import com.demo.security01.config.annotation.CommentAnno;
import com.demo.security01.model.dto.comment.request.CommentRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class CommentArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("====== CommentArgumentResolver ============");
        log.info("\t\t comment - supportsParameter called...");
        return parameter.hasParameterAnnotation(CommentAnno.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("\t\t comment = resolveArgument");
        return null;
    }
}
