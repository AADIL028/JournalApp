package com.demo.main.entity;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	
	
	
//	public LocalDateTime getDate() {
//		return date;
//	}
//	public void setDate(LocalDateTime date) {
//		this.date = date;
//	}
//	public ObjectId getId() {
//		return id;
//	}
//	public void setId(ObjectId id) {
//		this.id = id;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		this.content = content;
//	}
	
	
}
