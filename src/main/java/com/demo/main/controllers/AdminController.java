package com.demo.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.main.cache.AppCache;
import com.demo.main.entity.User;
import com.demo.main.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppCache appCache;
	
	@GetMapping("/all-users")
	public ResponseEntity<?> getAllUsers() {
		List<User> all = userService.getAllEntries();
		if(all!=null && !all.isEmpty()) {
			return new ResponseEntity<>(all, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/create-admin-user")
	public void createAdmin(@RequestBody User user) {
		userService.saveAdmin(user);
	}
	
	@GetMapping("/clear-app-cache")
	public void clearCache(){
		appCache.init();
	}
}
