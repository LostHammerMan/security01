package com.demo.security01.controller;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.security01.entity.user.User;
import com.demo.security01.service.category.CategoryService;
import com.demo.security01.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalController {

    private final CategoryService categoryService;
    private final UserService userService;

    @ModelAttribute() // @RequestMapping 이 붙은 컨트롤러 메서드가 호출되기 전 호출됨
    public void globalController(Model model, Principal principal){
    	
    	if(principal != null) {
    		User loginUser = userService.userDetailsByUsername(principal.getName());
    		model.addAttribute("loginUser", loginUser);
    	}
        model.addAttribute("categoryDtos", categoryService.getCategoryList());
//        log.info("categoryDtos = {}", categoryService.getCategoryList());
    }
    
    	
    	
}
