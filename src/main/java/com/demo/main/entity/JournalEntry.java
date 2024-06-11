package com.demo.main.entity;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.demo.main.enums.Sentiment;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document(collection = "journal_entries")
@Data		//lombok :- automatic generate •Getter,Setter,•RequiredArgsConstructor,•ToString,•EqualsAndHashCode,•lombok.Value
@NoArgsConstructor
public class JournalEntry {
	@Id
	private ObjectId id;
	@NonNull
	private String title;
	private String content;
	private LocalDateTime date;
	private Sentiment sentiment;
	
}
