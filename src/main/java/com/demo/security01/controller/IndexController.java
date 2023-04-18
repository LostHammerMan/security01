package com.demo.security01.controller;

import com.demo.security01.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping({"","/"})
    public String index(){
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }

    // 스프링 시큐리티가 해당 주소를 낚아챔 - Security Config 파일 생성 후 작동 안함
    @GetMapping("/loginForm")
    public String login(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String join(){
        return "joinForm";
    }

    @PostMapping("/join")
    public String Join(User user){
       return  "가입 완료";
    }

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc(){
        return "회원가입완료";
    }
}
