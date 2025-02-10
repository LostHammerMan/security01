package com.demo.security01.repository.test.MultipartFileTest;

import org.springframework.data.repository.CrudRepository;

import com.demo.security01.entity.test.User_redis;

public interface RedisTestRepository extends CrudRepository<User_redis, String> {
	
}
