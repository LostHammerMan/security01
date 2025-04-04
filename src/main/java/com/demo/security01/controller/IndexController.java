package com.demo.security01.controller;

import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.category.CategoryDto;import com.demo.security01.model.dto.crawling.StudyCrawlingResponeDto;
import com.demo.security01.model.dto.study.response.StudyResponseDto;
import com.demo.security01.service.category.CategoryService;
import com.demo.security01.service.study.StudyService;
import com.demo.security01.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

    private final CategoryService categoryService;
    private final StudyService studyService;
    private final UserService userService;
    
    @GetMapping({"","/"})
    public String index(Model model, Principal principal) {
        log.info("========== index Controller ===============");

        List<StudyResponseDto> recommendResults = new ArrayList<>();
        if(principal == null) {
        	recommendResults = studyService.getRecommendStudyNotLogIn();
        	
        	for(StudyResponseDto result : recommendResults) {
        		log.info("result.getDiffIndays() = " + result.getDiffInDays());
        	}
        	
        }else {
        	String username = principal.getName();
//        	User loginUser = userService.userDetailsByUsername(username);
        	recommendResults = studyService.getRecommendStudy(username);
//        	model.addAttribute("loginUser", loginUser);
        }
        log.info("recommendResults.size() = " + recommendResults.size());
        model.addAttribute("recommendResults", recommendResults);
        
        return "index";
    }
    
    @GetMapping("/index2")
    public String index2(){
        return "index2";
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

    @GetMapping("/flexTest")
    public String flexTest(){
        return "flexTest";
    }


}
