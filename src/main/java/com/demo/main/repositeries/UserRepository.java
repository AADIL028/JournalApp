package com.demo.main.repositeries;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.main.entity.JournalEntry;
import com.demo.main.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{
	User findByUsername(String username);
	void deleteByUsername(String username);
}
