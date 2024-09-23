package com.demo.security01.controller.api;

import com.demo.security01.model.dto.study.request.StudyCriteria;
import com.demo.security01.model.dto.study.request.StudyRequestDto;
import com.demo.security01.model.dto.study.response.StudyResponseDto;
import com.demo.security01.service.study.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudyApiController {

    private final StudyService studyService;

    @GetMapping("/study/studyList")
    public ResponseEntity<?> getStudyList(StudyCriteria criteria, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "9") int size){
        log.info("====== StudyApiController ==========");
        log.info("\t\t getStudyList called......");
        log.info("Criteria = {}", criteria);
        Pageable paging = PageRequest.of(page, size);
        List<StudyResponseDto> result = studyService.getStudyList(criteria, paging);
//        return result;
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
