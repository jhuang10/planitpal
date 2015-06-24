package com.planitpal.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = Plan.COLLECTION_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Plan {

    public static final String COLLECTION_NAME = "plan";

    @Id
    private String id;

    private String name;

    public static Plan plan(String name) {
        return new Plan(UUID.randomUUID().toString(), name);
    }
}
