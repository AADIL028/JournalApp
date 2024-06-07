package com.demo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager tmanager(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}
}

//atlas pass :- Mongo@20
//paadil820
//VqdhhUtbgjYDP023
//VqdhhUtbgjYDP023
//mongodb+srv://paadil820:<password>@cluster0.qqm23hk.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0
//p94yM6J0Q0rFmI1K