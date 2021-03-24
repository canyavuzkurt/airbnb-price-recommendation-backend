package com.pricerecommenders.airbnbpricerecommendation.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class AddToCollectionRequest {

    @NotEmpty(message = "recommendationId must not be empty.")
    private Long recommendationId;

    @NotEmpty(message = "collectionId must not be empty.")
    private Long collectionId;
}
