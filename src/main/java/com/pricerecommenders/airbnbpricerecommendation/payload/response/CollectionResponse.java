package com.pricerecommenders.airbnbpricerecommendation.payload.response;

import com.pricerecommenders.airbnbpricerecommendation.model.Collection;
import com.pricerecommenders.airbnbpricerecommendation.model.Recommendation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionResponse {

    private String name;
    private List<RecommendationResponse> recommendations;

    public CollectionResponse(Collection collection) {

        setName(collection.getName());
        setRecommendations(collection.getRecommendations().stream().sorted(Comparator.comparing(
                Recommendation::getCreatedAt).reversed()).map(RecommendationResponse::new).collect(Collectors.toList()));
    }
}
