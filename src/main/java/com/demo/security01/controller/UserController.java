package com.demo.security01.controller;

import com.demo.security01.entity.User;
import com.demo.security01.repository.UserRepositoryCustomImpl;
import com.demo.security01.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final UserRepositoryCustomImpl userRepositoryCustom;

    // 스프링 시큐리티가 해당 주소를 낚아챔 - Security Config 파일 생성 후 작동 안함
    @GetMapping("/loginForm")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String Join(User user) {

        userService.save(user);
        log.info("user = {}", user.getRole());
        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN") // ROLE_ADMIN 권한만 접근 가능 , 메서드에 사용, 하나의 권한만 사용 가능
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // data() 가 실행되기 전에 실행, 권한 여러 개 설정 가능
//    @PostAuthorize() // data() 실행 이후 실행
    @GetMapping("data")
    public @ResponseBody String data(){
        return "데이터 정보";
    }


}
