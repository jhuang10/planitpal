package com.planitpal.plan.templateservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Document(collection = "examples")
public class Example {

    @Id
    @JsonIgnore
    private String id;

    private String description;

    private String legacyId;

    private String colorId;

    private String marketId;

    public Example(String description, String legacyId, String colorId, String marketId) {
        this.description = description;
        this.legacyId = legacyId;
        this.colorId = colorId;
        this.marketId = marketId;
    }

}
