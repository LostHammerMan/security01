package com.demo.security01.config.handler;

import com.demo.security01.model.dto.paging.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class PagingHandler {

   /* @ExceptionHandler(BindException.class)
//    public ResponseEntity<Object> handleBindException(BindException e){
    public Criteria handleBindException(BindException e){

        BindingResult result = e.getBindingResult();

        // 검증 실패시 기본값 설정
        int defaultPerPageNum = 10;

        Criteria criteria = (Criteria) result.getTarget();
        criteria.setPerPageNum(defaultPerPageNum);
        return criteria;
    }*/
}
