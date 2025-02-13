package com.demo.security01.repository.redis;

import org.springframework.data.repository.CrudRepository;

import com.demo.security01.entity.redis.TempPw;

public interface RedisRepository extends CrudRepository<TempPw, String>{

}
