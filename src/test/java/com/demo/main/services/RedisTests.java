package com.demo.main.services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Disabled
	@Test
	public void test1() {
		redisTemplate.opsForValue().set("email", "Sample@email.com");
		Object email = redisTemplate.opsForValue().get("salary");
		System.out.println(email);
		int a = 1;
	}
}
