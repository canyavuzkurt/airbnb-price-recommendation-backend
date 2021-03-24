package com.pricerecommenders.airbnbpricerecommendation.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class AddToCollectionRequest {

    @NotNull(message = "recommendationId must not be null.")
    private Long recommendationId;

    @NotNull(message = "collectionId must not be null.")
    private Long collectionId;
}