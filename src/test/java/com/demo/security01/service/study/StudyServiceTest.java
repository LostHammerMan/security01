package com.demo.security01.service.study;

import com.demo.security01.model.dto.study.request.StudyRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class StudyServiceTest {

    @Autowired
    private StudyService studyService;

    @Test
    @DisplayName("스터디 저장 성공")
    void studyGenerateSuccess(){

        StudyRequestDto studyRequestDto = StudyRequestDto.builder()
                ..build();
    }
}