package com.example.girlscodeapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.girlscodeapi.model.repo")
public class MongoClientConnectionExample {

}
