package com.demo.main.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.main.entity.User;
import com.demo.main.repositeries.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
//	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public boolean saveNewUser(User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole(Arrays.asList("USER"));
			userRepository.save(user);
			return true;
		} catch (Exception e) {
				log.trace("Hahahahahahahaha");
				log.debug("Hahahahahahahaha");
				log.info("Hahahahahahahaha");
				log.warn("Hahahahahahahaha");
				log.error("Hahahahahahahaha");
//			log.error("error occured for {} :",user.getUsername(),e);
			return false;
		}
	}

	public List<User> getAllEntries() {
		return userRepository.findAll();
	}

	public Optional<User> getEntryById(ObjectId id) {
		return userRepository.findById(id);
	}

	public void deleteEntryById(ObjectId id) {
		userRepository.deleteById(id);
	}

	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	public void saveAdmin(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Arrays.asList("USER", "ADMIN"));
		userRepository.save(user);

	}
}
