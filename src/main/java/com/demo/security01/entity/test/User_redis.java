package com.demo.security01.entity.test;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RedisHash(value = "user_redis", timeToLive = 10)
@Setter @Getter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class User_redis {
	
	@Id // redis 키값
	private String id; 
	private String pw;
	private String major;
	private int age;

}
