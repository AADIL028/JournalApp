package com.demo.main.entity;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document(collection = "config_journal_app")
@Data		
@NoArgsConstructor
public class ConfigJournalEntity {

	private String key;
	private String value;
	
	
}
