package com.pricerecommenders.airbnbpricerecommendation.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginRequest {

    @NotEmpty(message = "First Name must not be empty.")
    private String fName;

    @NotEmpty(message = "Last Name must not be empty.")
    private String lName;

    @NotEmpty(message = "Token must not be empty.")
    private String apiToken;
}
