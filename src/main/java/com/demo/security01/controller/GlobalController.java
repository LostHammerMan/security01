package com.demo.security01.controller;

import com.demo.security01.repository.category.CategoryRepositoryCustom;
import com.demo.security01.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalController {

    private final CategoryService categoryService;

    @ModelAttribute()
    public void globalController(Model model){
        log.info("============= globalController ==================");
        model.addAttribute("categoryDtos", categoryService.getCategoryList());
        log.info("categoryDtos = {}", categoryService.getCategoryList());
    }
}