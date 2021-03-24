package com.pricerecommenders.airbnbpricerecommendation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "recommendations")
@Getter
@Setter
public class Recommendation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "recommendations")
    @JsonIgnore
    private List<Collection> collections;

    private String neighbourhood;

    private Double latitude;

    private Double longitude;

    private String propertyType;

    private String roomType;

    private Long accomodates;

    private Long beds;

    @ElementCollection
    private List<String> amenities;

    private Long price;

    private Long minimumNights;

    private Long maximumNights;

    private Boolean instantBookable;
}