package com.demo.security01.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test/test")
    public String test(){
        return "test/button_test";
    }
}
