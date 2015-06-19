package com.planitpal.plan.templateservice.configuration;

import com.mongodb.DB;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories("com.planitpal.plan.templateservice.domain.repository")
@ComponentScan("com.planitpal.plan.templateservice.domain.repository")
@EnableMongoAuditing(auditorAwareRef = "auditor")
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Autowired
    private PlanItPalServiceMongoConfig config;

    @Override
    protected String getDatabaseName() {
        return config.databaseName();
    }

    @Override
    @Bean(name = "planningMongo")
    public Mongo mongo() throws Exception {
        return MongoFactory.newInstance(config);
    }

    @Bean
    public DB planningDB(@Qualifier("planningMongo") Mongo planningMongo) {
        return planningMongo.getDB(getDatabaseName());
    }

    @Bean
    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
        return new CustomConversions(converterList);
    }

    @Override
    protected UserCredentials getUserCredentials() {
        return new UserCredentials(config.databaseUser(), config.databasePassword());
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.planitpal.plan.templateservice.domain";
    }
}
