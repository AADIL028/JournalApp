package com.demo.main.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.main.entity.ConfigJournalEntity;
import com.demo.main.repositeries.ConfigJournalAppRepository;

import jakarta.annotation.PostConstruct;

@Component
public class AppCache {
	
	public enum keys{
		WEATHER_API;
	}
	
	public Map<String, String> appCache;
	
	@Autowired
	private ConfigJournalAppRepository configJournalAppRepository;
	
	@PostConstruct
	public void init() {
		appCache = new HashMap<>();
		List<ConfigJournalEntity> all = configJournalAppRepository.findAll();
		for(ConfigJournalEntity configJournalEntity:all) {
			appCache.put(configJournalEntity.getKey(), configJournalEntity.getValue());
		}
	}
}
