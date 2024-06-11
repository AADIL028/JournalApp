package com.demo.main.services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.main.scheduler.UserScheduler;

@SpringBootTest
public class UserSchedulerTests {
	@Autowired
	private UserScheduler userScheduler;
	
	@Disabled
	@Test
	public void fetchUserAndSendSaMailTest() {
		userScheduler.fetchUserAndSendSaMail();
	}
}
