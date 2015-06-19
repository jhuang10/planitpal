package com.planitpal.plan.templateservice.configuration;

import org.aeonbits.owner.Config;

import java.util.List;

public interface PlanItPalServiceMongoConfig extends MongoConfig {

    @Config.Key("mongo.hosts")
    @Separator(",")
    List<String> databaseHost();

    @Key("mongo.dbName")
    String databaseName();

    @Key("mongo.username")
    String databaseUser();

    @Key("mongo.password")
    String databasePassword();

    @Key("mongo.writeVerifyNodes")
    @DefaultValue("1")
    int databaseWriteVerifyNumber();

    @Key("mongo.writeConcernBlockingStrategy")
    @DefaultValue("NONE")
    WriteConcernBlockingStrategy databaseWriteConcernBlockingStrategy();
}
