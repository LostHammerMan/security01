package com.demo.security01.controller;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.security01.model.BoardType;
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
        log.info("============= globalController ==================");
        model.addAttribute("categoryDtos", categoryService.getCategoryList());
//        log.info("categoryDtos = {}", categoryService.getCategoryList());
    }
    
    	
    	
}
