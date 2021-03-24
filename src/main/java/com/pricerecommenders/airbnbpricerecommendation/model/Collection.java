package com.pricerecommenders.airbnbpricerecommendation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "collections")
@Getter @Setter
public class Collection extends BaseEntity {

    @Column(name = "name")
    @NotEmpty
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToMany
    @NotEmpty
    @JoinTable(
            name = "collection_recommendations",
            joinColumns = @JoinColumn(name = "collection_id"),
            inverseJoinColumns = @JoinColumn(name = "recommendation_id")
    )
    private List<Recommendation> recommendations;
}
