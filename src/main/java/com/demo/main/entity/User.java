package com.demo.main.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document(collection = "users")
@Data		//lombok :- automatic generate •Getter,Setter,•RequiredArgsConstructor,•ToString,•EqualsAndHashCode,•lombok.Value
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	private ObjectId id;
	@Indexed(unique = true)
	@NonNull
	private String username;
	@NonNull
	private String password;
	private String email;
	private boolean sentimentAnalysis;
	@DBRef
	private List<JournalEntry> journalEntries = new ArrayList<>();
	
	private List<String> role;
}
