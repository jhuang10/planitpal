package com.planitpal.service.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Plan.COLLECTION_NAME)
public class Plan {

    public static final String COLLECTION_NAME = "plan";
    private String id;
}
