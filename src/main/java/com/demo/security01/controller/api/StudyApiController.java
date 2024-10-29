package com.demo.security01.controller.api;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.security01.model.dto.paging.PageResponseDto;
import com.demo.security01.model.dto.study.request.StudyCriteria;
import com.demo.security01.model.dto.study.request.StudyRequestDto;
import com.demo.security01.model.dto.study.response.StudyResponseDto;
import com.demo.security01.service.study.StudyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudyApiController {

    private final StudyService studyService;

    @GetMapping("/study/studyList")
    public ResponseEntity<?> getStudyList(StudyCriteria criteria,@PageableDefault(size = 12, page = 0)  Pageable pageable){
        log.info("====== StudyApiController ==========");
        log.info("\t\t getStudyList called......");
        PageResponseDto<StudyResponseDto> result = studyService.getStudyList(criteria, pageable);
        log.info("Criteria = {}", criteria);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/study/getListIsFin")
    public List<StudyResponseDto> getResult(StudyCriteria criteria){
        List<StudyResponseDto> result = studyService.getListByIsFin(criteria);
        return result;
    }
    
    @PostMapping("/study/write")
    public void generateStudy(StudyRequestDto request, Principal principal){
    	studyService.studyGenerate(request, principal.getName());
    		
    	
    }
}
