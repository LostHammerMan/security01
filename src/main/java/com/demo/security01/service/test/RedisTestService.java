package com.demo.security01.service.test;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.security01.entity.test.User_redis;
import com.demo.security01.repository.test.MultipartFileTest.RedisTestRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisTestService {
	
	private final RedisTestRepository repository;

	// 유저 등록
	@Transactional
	public User_redis addUser(User_redis user) {
		User_redis user1 = repository.save(user);	
		
		Optional<User_redis> findUser = repository.findById(user1.getId());
		log.info("findUser =" + findUser);
		
		return user1;
	}
	
	// 유저 조회(단건)
	@Transactional(readOnly = true)
	public User_redis getUserFindById(String userId) {
		Optional<User_redis> result = repository.findById(userId);
		log.info("result =" + result);
		
		if(result.isPresent()) {
			return result.get();
		}else {
			throw new RuntimeException("해당 유저는 존재하지 않음");
		}
	}
	
	// 모든 유저 조회
	@Transactional(readOnly = true)
	public List<User_redis> getAllUser(){
		List<User_redis> results = (List<User_redis>) repository.findAll();
		return results;
	}
	
}
