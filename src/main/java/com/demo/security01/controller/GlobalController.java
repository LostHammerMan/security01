package com.demo.security01.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.security01.service.category.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalController {

    private final CategoryService categoryService;

    @ModelAttribute() // @RequestMapping 이 붙은 컨트롤러 메서드가 호출되기 전 호출됨
    public void globalController(Model model){
        model.addAttribute("categoryDtos", categoryService.getCategoryList());
//        log.info("categoryDtos = {}", categoryService.getCategoryList());
    }
    
    	
    	
}
