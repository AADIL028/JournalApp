package com.demo.main.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.demo.main.entity.JournalEntry;
import com.demo.main.entity.User;
import com.demo.main.services.JournalService;
import com.demo.main.services.UserService;

@RestController
@RequestMapping("/journal")
public class JournalControllerV2 {

	@Autowired
	private JournalService journalService;

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> getAllJournalEntriesOfUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userService.findByUserName(username);
		List<JournalEntry> allEntries = user.getJournalEntries();
		if (allEntries != null && !allEntries.isEmpty()) {
			return new ResponseEntity<>(allEntries, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<JournalEntry> saveJournal(@RequestBody JournalEntry myJournal) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			journalService.saveEntry(myJournal, username);
			return new ResponseEntity<>(myJournal, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("id/{myId}")
	public ResponseEntity<JournalEntry> getOneEntryById(@PathVariable ObjectId myId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userService.findByUserName(username);
		List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
		if(!collect.isEmpty()) {
			Optional<JournalEntry> journalEntry = journalService.getEntryById(myId);
			if (journalEntry.isPresent()) {
				return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("id/{myId}")
	public ResponseEntity<?> deleteOneEntryById(@PathVariable ObjectId myId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		boolean removed = journalService.deleteEntryById(myId, username);
		if(removed) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("id/{id}")
	public ResponseEntity<?> updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newJ) 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userService.findByUserName(username);
		List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
		if(!collect.isEmpty()) {
			Optional<JournalEntry> journalEntry = journalService.getEntryById(id);
			if (journalEntry.isPresent()) {
				JournalEntry old = journalEntry.get();
				old.setTitle(newJ.getTitle() != null && !newJ.getTitle().equals("") ? newJ.getTitle() : old.getTitle());
				old.setContent(newJ.getContent() != null && !newJ.getContent().equals("") ? newJ.getContent() : old.getContent());
				
				journalService.saveEntry(old);
				return new ResponseEntity<>(old, HttpStatus.OK);
				
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
