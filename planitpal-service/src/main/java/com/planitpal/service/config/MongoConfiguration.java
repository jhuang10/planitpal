package com.planitpal.service.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(value = {
        "com.planitpal.service.domain.repository"
})
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient();
    }

    @Override
    protected String getDatabaseName() {
        return "plan";
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.planitpal.service.domain";
    }

}