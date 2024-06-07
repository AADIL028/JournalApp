package com.demo.main.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.demo.main.entity.JournalEntry;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_journal")
public class JournalController {
	private Map<Integer, JournalEntry> journalEntries = new HashMap<>();
	
	@GetMapping
	public List<JournalEntry> getAll(){
		return new ArrayList<>(journalEntries.values());
	}
	
	@PostMapping
	public boolean saveJournal(@RequestBody JournalEntry myJournal) {
//		journalEntries.put(myJournal.getId(), myJournal);
		return true;
	}
	
	@GetMapping("id/{myId}")
	public JournalEntry getOneEntryById(@PathVariable int myId) {
		return journalEntries.get(myId);
	}
	
	@DeleteMapping("id/{myId}")
	public JournalEntry deleteOneEntryById(@PathVariable int myId) {
		return journalEntries.remove(myId);
	}
	
	@PutMapping("id/{id}")
	public JournalEntry updateEntryById(@PathVariable int id,@RequestBody JournalEntry journal) {
		return journalEntries.put(id, journal);
	}
}
