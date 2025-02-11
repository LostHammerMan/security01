package com.demo.security01.entity.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RedisHash(value = "tempPw", timeToLive = 300)
@Data
@NoArgsConstructor @AllArgsConstructor
public class TempPw {
	
	@Id
	private String tempToken;
	private String email;
	
}
