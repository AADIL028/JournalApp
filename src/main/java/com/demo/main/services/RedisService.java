package com.demo.main.services;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService {
	@Autowired
	private RedisTemplate redisTemplate;

	public <T> T get(String key, Class<T> entityClass) {
		try {
			
			Object o = redisTemplate.opsForValue().get(key);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(o.toString(), entityClass);
			
		} catch (Exception e) {
			log.error("Exception " + e);
			return null;
		}
	}
	
	public void set(String key, Object o,Long expiryTime) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonValue = mapper.writeValueAsString(o);
			redisTemplate.opsForValue().set(key,jsonValue,expiryTime,TimeUnit.SECONDS);
			
		} catch (Exception e) {
			log.error("Exception " + e);
		}
	}
}
