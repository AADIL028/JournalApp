package com.demo.main.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.demo.main.cache.AppCache;
import com.demo.main.entity.JournalEntry;
import com.demo.main.entity.User;
import com.demo.main.enums.Sentiment;
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
		for (User user : users) {
			List<JournalEntry> journalEntries = user.getJournalEntries();
			List<Sentiment> sentiments = journalEntries.stream()
					.filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
					.map(x -> x.getSentiment()).collect(Collectors.toList());
			Map<Sentiment, Integer> sentimentCount = new HashMap<>();
			for (Sentiment sentiment : sentiments) {
				if (sentiment != null) {
					sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0) + 1);
				}
			}
			Sentiment mostFrequentSentiment = null;
			int maxCount = 0;
			for (Map.Entry<Sentiment, Integer> entry : sentimentCount.entrySet()) {
				if (entry.getValue() > maxCount) {
					maxCount = entry.getValue();
					mostFrequentSentiment = entry.getKey();
				}
			}
			if (mostFrequentSentiment != null) {
				emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", mostFrequentSentiment.toString());
			}

		}
	}

	@Scheduled(cron = "0 0/10 0 ? * *") // every 10 minutes
	public void clearAppCache() {
		log.info("Clearing cache.....");
		appCache.init();
	}
}
