package com.demo.security01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

   /* @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }*/

    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }


}
