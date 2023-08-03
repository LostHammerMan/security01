package com.demo.security01.controller.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class CommunityController {

    // 커뮤니티 메인, 개발자 라운지
    @GetMapping("/lounge")
    public String communityLounge(){
        return "community/commuLounge";
    }

    // QnA
    @GetMapping("/QnA")
    public String communityQnA(){
        return "community/commuQnA";
    }
}
