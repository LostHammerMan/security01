package com.demo.security01.service.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.security01.model.dto.user.JoinUserDto;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	// 회원가입
	@Test
	@DisplayName("유저 저장 성공")
	void userJoinTest() {
		JoinUserDto request = JoinUserDto.builder()
				.username("user123")
				.password("1234")
				.email_id("seong7577")
				.email_domain("@naver.com")
				.build();
			
	}
	
	// 회원 스킬 수정 test
	@Test
	@DisplayName("회원 수정 - 스킬")
	void userSkillModiFyTest() {
		
		List<Long> skillIdx = new ArrayList<>();
		skillIdx.add(1L);
		skillIdx.add(2L);
		skillIdx.add(3L);
		
		log.info("skillIdx = " + skillIdx);
		userService.skillTagModify(skillIdx, "admin");
	}
	
	// 회원 관심 스킬 추천 test

}
