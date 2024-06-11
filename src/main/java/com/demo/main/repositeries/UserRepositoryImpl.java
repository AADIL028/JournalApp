package com.demo.main.repositeries;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.demo.main.entity.User;

public class UserRepositoryImpl {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<User> getUserForSA() {
		Query query = new Query();
//		query.addCriteria(Criteria.where("username").is("Aadil"));
		query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"));
		query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
		List<User> user = mongoTemplate.find(query, User.class);
		return user;
	}
}
