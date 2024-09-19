package com.demo.security01.controller.study;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.security01.model.dto.study.request.StudyRequestDto;
import com.demo.security01.service.study.StudyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/study")
@Slf4j
public class StudyController {
	
	private final StudyService studyService;

    @GetMapping("")
    public String studyMain(){
        return "study/studyMain";
    }

    @GetMapping("/getStudyWriteForm")
    public String getStudyWriteFrom(HttpServletRequest request, Model model){
    	if(request.getUserPrincipal() == null){
//    		model.addAttribute("msg", "로그인이 필요한 기능입니다");
//    		model.addAttribute("url", request.getContextPath() + "/study");
    		return "error/loginError";
    	}
    	
        return "study/studyWriteForm";
    }
    
    @PostMapping("/writeProc")
    public String studyWrite(@ModelAttribute("studyRequestDto") StudyRequestDto studyRequestDto, Principal principal,
    		HttpServletRequest request, Model model) {
    	log.info("====== StudyController called.... ============");
    	log.info("\t\t studyRequestDto = {}", studyRequestDto);
    	
    	
    	studyService.studyGenerate(studyRequestDto, principal.getName());
    	request.setAttribute("msg", "스터디/프로젝트 작성 완료");
    	request.setAttribute("url", request.getContextPath() + "/study");
    	return "study/studyWriteOk";
    }
}
