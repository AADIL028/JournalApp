package com.demo.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.main.entity.User;
import com.demo.main.repositeries.JournalRepository;
import com.demo.main.repositeries.UserRepository;

@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JournalService journalService;
	
	@Disabled
	@Test
	public void testFindByUsername() {
		assertEquals(4, 2+2);
		assertNotNull(userRepository.findByUsername("admin"));
		assertTrue(journalService.deleteEntryById(new ObjectId("6660334ac79a9d0ee384d59e"),"Aadil"));
	}
	
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"1,2,3",
		"2,3,5",
		"5,5,10"
	})
	public void test(int a,int b,int expected) {
		assertEquals(expected, a+b);
	}
	
	@Disabled
	@ParameterizedTest
//	@CsvSource({
//		"Aadil",
//		"abc",
//		"admin"
//	})
	@ValueSource(strings={
			"Aadil",
			"abc",
			"admin"
		})
	public void test1(String name) {
		assertNotNull(userRepository.findByUsername(name),"failed for "+name);
	}
	
	@ParameterizedTest
	@ArgumentsSource(UserArgumentProvider.class)
	public void testSaveUser(User user) {
		assertTrue(userService.saveNewUser(user));
	}
	
//	@BeforeAll
//	public void setUp(){
//		
//	}
//	@BeforeEach
//	@AfterAll
//	@AfterEach
}
