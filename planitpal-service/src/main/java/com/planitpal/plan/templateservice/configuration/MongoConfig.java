package com.planitpal.plan.templateservice.configuration;

import org.aeonbits.owner.Config;

import java.util.List;

public interface MongoConfig extends Config {
    List<String> databaseHost();

    String databaseName();

    String databaseUser();

    String databasePassword();

    int databaseWriteVerifyNumber();

    WriteConcernBlockingStrategy databaseWriteConcernBlockingStrategy();

    static enum WriteConcernBlockingStrategy {
        JOURNALING, FSYNC, NONE
    }
}
