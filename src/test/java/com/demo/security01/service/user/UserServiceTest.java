package com.demo.security01.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.user.JoinUserDto;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailServiceImpl mailServiceImpl;
	
	// 비밀번호 재설정 링크 메일 테스트
	@Test
	void sendResetPwLinkTest() throws Exception {
		userService.sendResetPwLink("seong9385@gmail.com");
		
	}
	
	// 메일 전송 테스트용
	@Test
	void sendEmail() throws Exception {
		mailServiceImpl.createMessage("seong9385@gmail.com");
	}
	
	// 레디스 조회
	@Test
	void getRedisData() {
		userService.getRedisData("d6fed295-801e-4e41-86c2-40f0b5cc6152");
	}
	
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
//		skillIdx.add(2L);
//		skillIdx.add(3L);
		
		log.info("skillIdx = " + skillIdx);
		userService.skillTagModify(skillIdx, "admin");
	}
	
	// 회원 조회
	@Test
	@DisplayName("회원조회 - 이메일")
	void getUserByEmail() {
		String email = "seong7577@naver.com";
		User user =  userService.getUserByEmail(email);
		log.info("user = " + user.getUsername());
	}
	
	// 회원 관심 스킬 추천 test

}
