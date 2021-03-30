package com.pricerecommenders.airbnbpricerecommendation.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.List;

@Getter
@Setter
public class RecommendationFilter {

    private String neighbourhoodFilter;

    private Double latitudeFilter;

    private Double longitudeFilter;

    private String propertyTypeFilter;

    private String roomTypeFilter;

    private Long accommodatesFilter;

    private Long bedsFilter;

    @ElementCollection
    private List<String> amenitiesFilter;

    private Long priceFilter;

    private Long minimumNightsFilter;

    private Long maximumNightsFilter;

    private Boolean instantBookableFilter;
}
