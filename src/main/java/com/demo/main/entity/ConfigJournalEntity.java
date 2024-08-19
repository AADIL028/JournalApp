package com.demo.main.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "config_journal_app")
@Data		
@NoArgsConstructor
public class ConfigJournalEntity {

	private String key;
	private String value;
	
	
}
