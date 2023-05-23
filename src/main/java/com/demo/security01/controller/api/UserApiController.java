package com.demo.security01.controller.api;

import com.demo.security01.model.dto.EmailDto;
import com.demo.security01.model.dto.ResponseEntityDto;
import com.demo.security01.service.MailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        session.setAttribute("authCode", code);

        return code;
    }

    //@PostMapping("/authNumCheck")
    @GetMapping(value = {"/authNumCheck","/authNumCheck{inputAuthNum}"}) // {auth_code} 가 null 인 경우를 위해 "/authNumCheck" 사용
    public ResponseEntity<Object> authNumCheck(@PathVariable String inputAuthNum, HttpSession session){
        log.info("================= inputAuthNum called ===================");
        log.info("inputAuthNum = {}", inputAuthNum);

        String authCode = (String) session.getAttribute("authCode");
        log.info("authCode = {}", authCode);

        if (!authCode.equals(inputAuthNum)){
            throw new IllegalArgumentException("인증코드가 일치하지 않음");
//            throw new IllegalArgumentException(getMe);
            /*ResponseEntityDto responseEntityDto = ResponseEntityDto.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("인증코드가 일치하지 않음!!").build();

            throw new
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseEntityDto);*/
        }

        ResponseEntityDto responseEntityDto = ResponseEntityDto.builder()
                .status(HttpStatus.OK.value())
                .message("인증 성공!!").build();

        return ResponseEntity.status(HttpStatus.OK).body(responseEntityDto);



//        Response response = new Response();

       /* if (auth_code == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("입력값이 없음");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400
//            throw new IllegalArgumentException("입력 값이 없음");
            throw new NullPointerException("인증코드가 입력되지 않았습니다.");
//            throw new MissingPathVariableException(au, auth_code);

        } else if (!authNum.equals(auth_code)) {
            return null;
//            response.setStatus(HttpStatus.NOT_FOUND.value());
//            response.setMessage("인증코드 다시 확인!");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);  // 404
        } else if(authNum.equals("")){
            return new MissingPathVariableException(authNum, auth_code);
        }

//        response.setStatus(HttpStatus.OK.value());
//        response.setMessage("인증코드 확인되었습니다");

//        return new ResponseEntity<>(response, HttpStatus.OK); // 200*/

    }

}
