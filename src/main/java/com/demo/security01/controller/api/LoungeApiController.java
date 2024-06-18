package com.demo.security01.controller.api;

import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.model.dto.community.LoungeListResponseDto;
import com.demo.security01.service.community.LoungeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoungeApiController {

    private final LoungeService loungeService;

    @GetMapping({"/api/loungeList"})
    public ResponseEntity<?> communityLoungeWithPaging(@RequestParam(name = "lastIdx", required = false) Long lastIdx){
        log.info("========= controller - communityLoungeWithPaging =========");
//        log.info("lastIdx = {}", lastIdx);
        List<LoungeListResponseDto> allLounge = loungeService.getAllLoungeWithPaging(lastIdx);
        return ResponseEntity.ok().body(allLounge);
    }
}
