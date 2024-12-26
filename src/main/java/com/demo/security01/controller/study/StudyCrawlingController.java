package com.demo.security01.controller.study;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.security01.model.StudySite;
import com.demo.security01.model.dto.crawling.StudyCrawlingResponeDto;
import com.demo.security01.service.study.StudyCrawlingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class StudyCrawlingController {
	
	private final StudyCrawlingService studyCrawlingService;
	
	
	// 크롤링 테스트
//	public List<StudyCrawlingResponeDto> getStudyHtml(){
	@GetMapping("/getStudyScrap")
	public ResponseEntity<?> getStudyHtml(@RequestParam(name="site") StudySite site){
		log.info("============ StudyCrawlingController ==================");
		Map<Enum<StudySite>, List<StudyCrawlingResponeDto>> results = new HashMap<Enum<StudySite>, List<StudyCrawlingResponeDto>>();
		if(site == StudySite.INFLEARN) {
			results = studyCrawlingService.getScrapInflearn();
		}else if (site == StudySite.SOUP) {
			results = studyCrawlingService.getScrapSoup();
		}else {
			log.info("존재하지 않는 요청입니다");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청입니다");
		}
		
		return ResponseEntity.ok(results);
//		Map<Enum<StudySite>, List<StudyCrawlingResponeDto>> results = studyCrawlingService.getScrapInflearn();
		
//		Map<Enum<StudySite>, List<StudyCrawlingResponeDto>> results2 = studyCrawlingService.getScrapLetspl();
//		Map<Enum<StudySite>, List<StudyCrawlingResponeDto>> results2 = studyCrawlingService.getScrapHola11();
//		return results;
//		return ResponseEntity.ok(results);
//		
		
	}

}
