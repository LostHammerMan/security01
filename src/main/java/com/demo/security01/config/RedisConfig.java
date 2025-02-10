package com.demo.security01.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.demo.security01.entity.redis.TempPw;
import com.demo.security01.entity.test.User_redis;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableRedisRepositories
public class RedisConfig {
	
	private final RedisProperties redisProperties;
	
	// lettuce 관련 설정
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
	}
	
	// 임시비밀번호
	@Bean
	public RedisTemplate<?, ?> redisTemplate(){
		RedisTemplate<?, ?> redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		
		Jackson2JsonRedisSerializer<TempPw> valueSerializer = new Jackson2JsonRedisSerializer<>(TempPw.class);
		redisTemplate.setValueSerializer(valueSerializer);
		return redisTemplate;
	}
	
	// redis template  - 테스트용
	/* 
	 * key, value Serializer 사용 이유
	 * 	- 스프링과 redis 간 데이터 직렬, 역직렬화 방식이 jdk 방식이기 때문
	 *  - redis-cli를 통해 data 직접 조회시 해당 설정 필요	
	 * 
	 * */
	@Bean
	public RedisTemplate<?, ?> redisTemplate2(){
		RedisTemplate<?, ?> redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		
		// 키
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		
		// 값
		Jackson2JsonRedisSerializer<User_redis> valueSerializer = new Jackson2JsonRedisSerializer<>(User_redis.class);
		redisTemplate.setValueSerializer(valueSerializer);
		return redisTemplate;
	}
	
	
}
