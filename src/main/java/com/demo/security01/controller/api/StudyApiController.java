package com.demo.security01.controller.api;

import com.demo.security01.model.dto.study.request.StudyCriteria;
import com.demo.security01.model.dto.study.response.StudyResponseDto;
import com.demo.security01.service.study.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudyApiController {

    private final StudyService studyService;

    @GetMapping("/study/studyList")
    public List<StudyResponseDto> getStudyList(StudyCriteria criteria, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size){
        log.info("====== StudyApiController ==========");
        log.info("\t\t getStudyList called......");
        log.info("Criteria = {}", criteria);
        Pageable paging = PageRequest.of(page, size);
        List<StudyResponseDto> result = studyService.getStudyList(criteria, paging);
        return result;
    }
}
