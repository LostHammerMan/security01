package com.demo.security01.controller.api;

import com.demo.security01.model.dto.EmailDto;
import com.demo.security01.model.dto.JoinUserDto;
import com.demo.security01.service.MailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private MailServiceImpl mailService;

    @PostMapping("/emailAuth")
    public String emailAuth(@RequestBody EmailDto emailDto) throws Exception {
        log.info("=========== emailAuth called..... ==============");
        log.info("sendAuthEmail, {}", emailDto);

        String email_id = emailDto.getEmail_id();
        String email_domain = emailDto.getEmail_domain();
        String email_addr = email_id + email_domain;
        log.info(email_addr);
//        log.info("email_addr = " + email_addr);

        String code = mailService.sendSimpleMessage(email_addr);
//        log.info("인증코드 = " + code);
        return code;
    }
}
