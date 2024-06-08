package com.demo.main.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.main.repositeries.UserRepository;

public class UserDetailServiceImplTests {
	
	@InjectMocks
	private UserDetailServiceImpl detailServiceImpl;
	
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Disabled
	@Test
	void loadUserByUsernameTest() {
		when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(com.demo.main.entity.User.builder().username("Aadil").password("abagvsthsb").role(new ArrayList<>()).build());
		UserDetails user = detailServiceImpl.loadUserByUsername("Aadil");
		assertNotNull(user);
	}
}
