package com.demo.main.services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
	@Autowired
	private EmailService emailService;
	@Disabled
	@Test
	public void sendEmailTest() {
		emailService.sendEmail("mr.aadil7860@gmail.com", "Java Mail Test", "Hello, this is sent from java mailsender.");
	}
}
