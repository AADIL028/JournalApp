package com.demo.main.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.demo.main.cache.AppCache;
import com.demo.main.entity.JournalEntry;
import com.demo.main.entity.User;
import com.demo.main.repositeries.UserRepositoryImpl;
import com.demo.main.services.EmailService;
import com.demo.main.services.SentimentalAnalysisService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserScheduler {

	@Autowired
	private UserRepositoryImpl userRepositoryImpl;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private SentimentalAnalysisService sentimentalAnalysisService;
	
	@Autowired
	private AppCache appCache;
	
//	@Scheduled(cron = "0 0 9 ? * SUN")
	public void fetchUserAndSendSaMail() {
		List<User> users = userRepositoryImpl.getUserForSA();
		for(User user:users) {
			List<JournalEntry> journalEntries = user.getJournalEntries();
			List<String> filteredEntries = journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7,ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
			String entry = String.join(" ", filteredEntries);
			String sentiment = sentimentalAnalysisService.getSentiment(entry);
//			emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment);
		}
	}
	
	@Scheduled(cron="0 0/10 0 ? * *")		//every 10 minutes
	public void clearAppCache() {
		log.info("Clearing cache.....");
		appCache.init();
	}
}
