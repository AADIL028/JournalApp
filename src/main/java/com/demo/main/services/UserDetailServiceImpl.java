package com.demo.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties.ConfigureAction;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import com.demo.main.entity.User;
import com.demo.main.repositeries.UserRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user!=null) {
			UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
					.username(user.getUsername())
					.password(user.getPassword())
					.roles(user.getRole().toArray(new String[0]))
					.build();
			return userDetails;
		}
		throw new UsernameNotFoundException("User not found with usename : "+username);
	}
	
}
