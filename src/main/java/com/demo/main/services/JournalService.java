package com.demo.main.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.main.entity.JournalEntry;
import com.demo.main.entity.User;
import com.demo.main.repositeries.JournalRepository;

@Service
public class JournalService {

	@Autowired
	private JournalRepository journalRepository;

	@Autowired
	private UserService userService;

	@Transactional
	public void saveEntry(JournalEntry journalEntry, String username) {
		User user = userService.findByUserName(username);
		journalEntry.setDate(LocalDateTime.now());
		JournalEntry saved = journalRepository.save(journalEntry);
		user.getJournalEntries().add(saved);
//		user.setUsername(null);
		userService.saveUser(user);
	}

	public void saveEntry(JournalEntry journalEntry) {
		journalRepository.save(journalEntry);
	}

	public List<JournalEntry> getAllEntries() {
		return journalRepository.findAll();
	}

	public Optional<JournalEntry> getEntryById(ObjectId id) {
		return journalRepository.findById(id);
	}

	@Transactional
	public boolean deleteEntryById(ObjectId id, String username) {
		boolean removed = false;
		try {
			User user = userService.findByUserName(username);
			removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id)); // if not handled here.spring will
																					// remove ref on
																					// next save method call.
			if (removed) {
				userService.saveUser(user);
				journalRepository.deleteById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("En error occurred while deleting entry....", e);
		}
		return removed;
	}

}
