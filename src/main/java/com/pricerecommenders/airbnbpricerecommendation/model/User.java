package com.pricerecommenders.airbnbpricerecommendation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

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
    @Size(max = 1500)
    private String apiToken;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Recommendation> recommendations;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Collection> collections;

    public User(@NotBlank String fName, @NotBlank String lName, String apiToken) {

        this.fName = fName;
        this.lName = lName;
        this.apiToken = apiToken;
    }
}