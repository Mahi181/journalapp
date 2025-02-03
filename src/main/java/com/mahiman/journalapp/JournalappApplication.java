package com.mahiman.journalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //this is used to enable the transaction management in the application
public class JournalappApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalappApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) { //this is a bean which is used to manage the transaction in the database
		return new MongoTransactionManager(dbFactory); //this is a bean which is used to manage the transaction in the database helps to rollback the transaction if any error occurs and connect to the database
	}

}
