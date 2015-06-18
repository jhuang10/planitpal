package com.planitpal.plan.templateservice.view;

import com.planitpal.plan.templateservice.domain.Example;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ExampleResource {

    private String description;

    private String legacyId;

    private String colorId;

    private String marketId;

    public ExampleResource(String description, String legacyId, String colorId, String marketId) {
        this.description = description;
        this.legacyId = legacyId;
        this.colorId = colorId;
        this.marketId = marketId;
    }

    public Example createStyle() {
        return new Example(description, legacyId, colorId, marketId);
    }
}
