package com.demo.security01.controller.api;

import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.service.community.LoungeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoungeApiController {

    private final LoungeService loungeService;

    @GetMapping({"/lounge", ""})
    public ResponseEntity<?> communityLoungeWithPaging(Long lastIdx){
        List<LoungeEntity> allLounge = loungeService.getAllLoungeWithPaging(lastIdx);
        return ResponseEntity.ok().body(allLounge);
    }
}
