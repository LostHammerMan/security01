package com.demo.security01.controller.api;

import com.demo.security01.model.dto.EmailDto;
import com.demo.security01.model.dto.Response;
import com.demo.security01.service.MailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserApiController {


    @Autowired
    private MailServiceImpl mailService;

    @PostMapping("/emailAuth")
    public String emailAuth(@RequestBody EmailDto emailDto, HttpSession session) throws Exception {
        log.info("=========== emailAuth called..... ==============");
        log.info("sendAuthEmail, {}", emailDto);

        String email_id = emailDto.getEmail_id();
        String email_domain = emailDto.getEmail_domain();
        String email_addr = email_id + email_domain;
        log.info(email_addr);
//        log.info("email_addr = " + email_addr);

        String code = mailService.sendSimpleMessage(email_addr);
        log.info("code = {}", code);

        Map<String, String> emailCheck = new HashMap<>();
        emailCheck.put(email_addr, code);
        log.info("emailCheck = {}", emailCheck);

        session.setAttribute("emailCheck", emailCheck);
        session.setAttribute("code", code);

        return code;
    }

    //@PostMapping("/authNumCheck")
    @GetMapping(value = {"/authNumCheck","/authNumCheck/{auth_code}"})
    public ResponseEntity<Object> authNumCheck(@PathVariable(required = false) String auth_code, HttpSession session){
        log.info("================= authNumCheck called ===================");
        log.info("auth_code = {}", auth_code);

        String authNum = (String) session.getAttribute("code");
        log.info("authNum = {}", authNum);

//        Response response = new Response();

        if (auth_code == null) {
            /*response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("입력값이 없음");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400*/
            throw new IllegalArgumentException("입력 값이 없음");
        } else if (!authNum.equals(auth_code)) {
            return null;
//            response.setStatus(HttpStatus.NOT_FOUND.value());
//            response.setMessage("인증코드 다시 확인!");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);  // 404
        }

//        response.setStatus(HttpStatus.OK.value());
//        response.setMessage("인증코드 확인되었습니다");

//        return new ResponseEntity<>(response, HttpStatus.OK); // 200
        return null;
    }

}
