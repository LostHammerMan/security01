package com.demo.security01.controller.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class StudyController {

    @GetMapping("")
    public String studyMain(){
        return "study/studyMain";
    }

    @GetMapping("/getStudyWriteForm")
    public String getStudyWriteFrom(){
        return "study/studyWriteForm";
    }
}
