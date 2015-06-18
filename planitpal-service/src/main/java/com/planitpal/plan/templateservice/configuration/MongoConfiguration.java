package com.planitpal.plan.templateservice.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories("com.gap.plan.templateservice.domain.repository")
@ComponentScan("com.gap.plan.templateservice.domain.repository")
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Value("${mongo.dbName}")
    private String databaseName;

    @Value("${mongo.username}")
    private String username;

    @Value("${mongo.password}")
    private String password;

    @Value("${mongo.hosts}")
    private String[] hosts;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public Mongo mongo() throws Exception {
        List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
        for (String host : hosts) {
            serverAddresses.add(new ServerAddress(host));
        }

        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        builder.connectTimeout(5);
        MongoClientOptions mongoClientOptions = builder.build();
        MongoClient mongoClient = new MongoClient(serverAddresses, mongoClientOptions);

        return mongoClient;
    }

    @Override
    protected UserCredentials getUserCredentials() {
        return new UserCredentials(username, password);
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.gap.plan.templateservice.domain.entity";
    }
}
