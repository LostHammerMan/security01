package com.demo.security01.controller.api;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.security01.model.dto.community.LoungeCriteria;
import com.demo.security01.model.dto.community.LoungeListResponseDto;
import com.demo.security01.service.community.LoungeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoungeApiController {

    private final LoungeService loungeService;

    @GetMapping({"/api/loungeList"})
    public ResponseEntity<?> getLoungeWithPaging(@RequestParam(name = "lastIdx", required = false) Long lastIdx, LoungeCriteria cri){
        log.info("========= controller - communityLoungeWithPaging =========");
        log.info("lastIdx = {}", lastIdx);
        List<LoungeListResponseDto> allLounge = loungeService.getAllLoungeWithPaging(lastIdx, cri);
        return ResponseEntity.ok().body(allLounge);
    }
    
    @GetMapping("/api/loungeList/like")
    public ResponseEntity<?> getLoungeWithLikeCheck(@RequestParam(name = "lastIdx", required = false) Long lastIdx, LoungeCriteria cri, Principal principal){
    	if(principal == null) {
    		new RuntimeException("로그인이 필요한 기능입니다");
    	}
    	
    	String loginUsername = principal.getName();
    	
    	List<LoungeListResponseDto> results = loungeService.get
    	
    	return ResponseEntity.ok().body(null);
    }
}
