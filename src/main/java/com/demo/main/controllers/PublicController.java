package com.demo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.main.entity.User;
import com.demo.main.services.UserDetailServiceImpl;
import com.demo.main.services.UserService;
import com.demo.main.utils.JwtUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailServiceImpl userDetailService;
	@Autowired
	private JwtUtils jwtUtils;

	@GetMapping("/health-check")
	public String helthChecker() {
		return "Ok";
	}

	@PostMapping("/signup")
	public void signup(@RequestBody User user) {
		userService.saveNewUser(user);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());
			String jwt = jwtUtils.generateToken(userDetails.getUsername());
			return new ResponseEntity<>(jwt, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception occured while generating token...",e);
			return new ResponseEntity<>("Incorrect username or password....",HttpStatus.BAD_REQUEST);
		}
	}
}
