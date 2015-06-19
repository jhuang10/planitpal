package com.planitpal.plan.templateservice.configuration;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.mongodb.*;
import lombok.SneakyThrows;

import java.net.UnknownHostException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Collections2.transform;
import static com.planitpal.plan.templateservice.configuration.MongoConfig.WriteConcernBlockingStrategy.FSYNC;
import static com.planitpal.plan.templateservice.configuration.MongoConfig.WriteConcernBlockingStrategy.JOURNALING;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.StringUtils.isBlank;

public final class MongoFactory {

    private MongoFactory() {
        //do nothing
    }

    public static Mongo newInstance(MongoConfig mongoConfig) {
        checkArgument(mongoConfig.databaseWriteVerifyNumber() > 0);

        MongoConfig.WriteConcernBlockingStrategy writeConcernBlockingStrategy = mongoConfig.databaseWriteConcernBlockingStrategy();
        WriteConcern writeConcern = new WriteConcern(mongoConfig.databaseWriteVerifyNumber(), 5000, writeConcernBlockingStrategy == FSYNC, writeConcernBlockingStrategy == JOURNALING);
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder()
                .connectTimeout(2 * 1000)
                .writeConcern(writeConcern)
                .socketTimeout(15000) // SO_TIMEOUT - Longest read delay
                .connectionsPerHost(5)
                .build();

        List<ServerAddress> serverAddresses = getHostsAsServerAddresses(mongoConfig.databaseHost());

        String dbPassword = mongoConfig.databasePassword();
        if (isBlank(dbPassword)) {
            return new MongoClient(serverAddresses, mongoClientOptions);
        }

        MongoCredential credential = MongoCredential.createMongoCRCredential(mongoConfig.databaseUser(), mongoConfig.databaseName(), dbPassword.toCharArray());

        return new MongoClient(serverAddresses, singletonList(credential), mongoClientOptions);
    }

    private static List<ServerAddress> getHostsAsServerAddresses(List<String> serverHosts) {
        return ImmutableList.copyOf(transform(serverHosts, new Function<String, ServerAddress>() {
            @Override
            @SneakyThrows(UnknownHostException.class)
            public ServerAddress apply(
                    String hostPort) {
                String[] tokens = hostPort.split(":");
                String host = tokens[0];
                int port = (tokens.length > 1) ? Integer.parseInt(tokens[1]) : 27017;
                return new ServerAddress(host, port);
            }
        }));
    }
}
