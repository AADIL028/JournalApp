package com.demo.main.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.main.repositeries.UserRepositoryImpl;

@SpringBootTest
public class UserRepositoryImplTests {
	
	@Autowired
	private UserRepositoryImpl implTests;
	
	@Disabled
	@Test
	public void getUserForSATest() {
//		assertNotNull(implTests.getUserForSA());
		System.out.println(implTests.getUserForSA());
	}
}
