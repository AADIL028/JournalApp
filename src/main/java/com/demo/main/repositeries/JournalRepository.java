package com.demo.main.repositeries;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.main.entity.JournalEntry;

public interface JournalRepository extends MongoRepository<JournalEntry, ObjectId>{

}
