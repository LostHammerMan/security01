package com.demo.security01.config.resolver;

import com.demo.security01.config.annotation.LoungeWriteRequestAnno;
import com.demo.security01.config.exception.CateCodeFormantException;
import com.demo.security01.model.dto.community.LoungeWriteRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class LoungeRequestArgumentResolver implements HandlerMethodArgumentResolver {

    // 메서드의 파라미터가 LoungeWriteRequest 타입인지 확인
    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        log.info("======== LoungeRequestArgumentResolver called ==========");
        log.info("\t\t supportsParameter called.....");
        return parameter.hasParameterAnnotation(LoungeWriteRequestAnno.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("\t\t resolveArgument called....");
        LoungeWriteRequest loungeWriteRequest = new LoungeWriteRequest();

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String cateCodeParam = request.getParameter("cateCode");
        String title = request.getParameter("title");
        String content = request.getParameter("contents");
        log.info("\t\t cateCodeParam = {}, title = {}, content = {}", cateCodeParam, title, content);
//        Long cateCode = null;

        // json 사용시
//        ObjectMapper mapper = new ObjectMapper();
//        LoungeWriteRequest loungeWriteRequest2 = mapper.readValue(request.getReader(), LoungeWriteRequest.class);
//
//        return loungeWriteRequest2;

//        if (cateCodeParam != null){
//            try {
//                cateCode = Long.parseLong(cateCodeParam);
//            }catch (NumberFormatException e){
//                e.printStackTrace();
//                throw new CateCodeFormantException();
//            }
//        }

//        cateCode = cateCodeParam != null ? Long.parseLong(cateCodeParam) : null;

//        log.info("\t\t\tcateCodeParam = {}", cateCodeParam);
//        log.info("\t\t\tcateCode = {}", cateCode);
//        if (cateCode != null && (cateCode == 5 || cateCode == 6)) {
//            loungeWriteRequest.setCateCode(cateCode);
//        } else {
//            loungeWriteRequest.setCateCode(5L);
//        }

//        log.info("request.dto.getCateCode = {}", loungeWriteRequest.getCateCode());
        Long cateCode = null;
        try {
            cateCode = Long.parseLong(cateCodeParam);
        } catch (Exception e) {
            cateCode = null;
        }
        loungeWriteRequest.setCateCode(cateCode);
        loungeWriteRequest.setTitle(title);
        loungeWriteRequest.setContents(content);
        log.info("\t\t resolved loungeWriteRequest = {}", loungeWriteRequest);

        return loungeWriteRequest;
    }
}
