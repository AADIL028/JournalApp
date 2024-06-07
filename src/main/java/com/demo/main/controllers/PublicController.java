package com.demo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.main.entity.User;
import com.demo.main.services.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/health-check")
	public String helthChecker() {
		return "Ok";
	}
	
	@PostMapping("/create-user")
	public void saveUser(@RequestBody User user) {
		userService.saveNewUser(user);
	}
}
