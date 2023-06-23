package com.demo.security01.controller.test;

import com.demo.security01.model.dto.TestDto;
import com.demo.security01.model.dto.test.TestValidatorDto;
import com.demo.security01.validator.test.TestEmailAddrValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@Slf4j
@Validated
public class TestController {

   /* @Resource(name = "testEmailAddrValidator")
    private TestEmailAddrValidator testEmailAddrValidator;
*/


    @GetMapping("/test/test")
    public String test(){
        return "test/button_test";
    }

    @GetMapping("/test/testDto")
    public String test2(){
        return "test/testDto";
    }

    @PostMapping("/test/testDto")
    @ResponseBody
    public ResponseEntity<Object> test3(@RequestBody @Validated TestValidatorDto testValidatorDto, BindingResult result){
        log.info("===== test3 called =======");
        log.info("testValidatorDto ={}", testValidatorDto);

        if (result.hasErrors()){
            return ResponseEntity.badRequest().body("Test 실패");
        }
        return ResponseEntity.ok("Test 성공");
    }

}
