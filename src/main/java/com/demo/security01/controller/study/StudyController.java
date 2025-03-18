package com.demo.security01.controller.study;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.study.request.StudyRequestDto;
import com.demo.security01.model.dto.study.response.StudyResponseDto;
import com.demo.security01.service.study.StudyService;
import com.demo.security01.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/study")
@Slf4j
public class StudyController {
	
	private final StudyService studyService;
	private final UserService userService;

    @GetMapping("")
    public String studyMain(Model model){
		/*
		 * if(principal != null) { User loginUser =
		 * userService.userDetailsByUsername(principal.getName());
		 * model.addAttribute("loginUser", loginUser); }
		 */
        return "study/studyMain";
    }

    @GetMapping("/getStudyWriteForm")
    public String getStudyWriteFrom(Principal principal, Model model){
    	if(principal.getName() == null){
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
    
    // 스터디 단건 조회
    @GetMapping("/{studyIdx}")
    public String getStudy(@PathVariable Long studyIdx, Model model, HttpServletRequest request) {
    	log.info("\t\t 스터디 단건 조회");
//    	String loginUsername = principal.getName();
    	String loginUsername = request.getUserPrincipal().getName();
    	StudyResponseDto result = studyService.getStudy(studyIdx);
    	boolean isLikeCheck = studyService.isCheckLike(studyIdx, loginUsername);
    	
    	model.addAttribute("result", result);
    	model.addAttribute("loginUsername", loginUsername);
    	model.addAttribute("loginUserImgFileName", userService.getProfileFileName(loginUsername));
    	model.addAttribute("isLikeCheck", isLikeCheck);
    	
    	return "study/studyReadForm";
    }
}
