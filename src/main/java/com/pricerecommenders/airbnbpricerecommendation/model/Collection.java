package com.pricerecommenders.airbnbpricerecommendation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "collections")
@Getter @Setter
public class Collection extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY)
    @NotEmpty
    private List<Recommendation> recommendations;
}
