package com.pricerecommenders.airbnbpricerecommendation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User extends BaseEntity{

    @Column(name = "f_name")
    @NotBlank
    private String fName;

    @Column(name = "l_name")
    @NotBlank
    private String lName;

    @Column(name = "api_token")
    private String apiToken;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Recommendation> recommendations;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Collection> collections;

    public User(@NotBlank String fName, @NotBlank String lName, String apiToken) {

        this.fName = fName;
        this.lName = lName;
        this.apiToken = apiToken;
    }
}