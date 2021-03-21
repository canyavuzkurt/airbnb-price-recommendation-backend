package com.pricerecommenders.airbnbpricerecommendation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "recommendations")
@Getter @Setter
public class Recommendation extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;
}
