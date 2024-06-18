package com.demo.security01.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {

    @GetMapping("/api/testController")
    public String testController(){
        return "123123";
    }
}
