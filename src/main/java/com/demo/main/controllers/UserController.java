package com.demo.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.main.entity.User;
import com.demo.main.repositeries.UserRepository;
import com.demo.main.response.WeatherResponse;
import com.demo.main.response.WeatherResponse.Main;
import com.demo.main.services.JournalService;
import com.demo.main.services.UserService;
import com.demo.main.services.WeatherService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WeatherService weatherService;
	
//	@GetMapping
//	public List<User> getAll(){
//		return userService.getAllEntries();
//	}
	
	
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User userInDb = userService.findByUserName(username);
		if(userInDb!=null) {
			userInDb.setUsername(user.getUsername());
			userInDb.setPassword(user.getPassword());
			userService.saveNewUser(userInDb);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userRepository.deleteByUsername(authentication.getName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<?> greeting(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		WeatherResponse response = weatherService.getWeather("Ahmedabad");
		String greeting = "";
		if(response != null) {
			//converting kelvin to Celsius.
			double feelsLike = response.getMain().getFeelsLike() - 273.15;
			greeting=", Weather feels like :- " + String.format("%.2f", feelsLike);
		}
		return new ResponseEntity<>("Hi, "+authentication.getName()+greeting,HttpStatus.OK);
	}
}
