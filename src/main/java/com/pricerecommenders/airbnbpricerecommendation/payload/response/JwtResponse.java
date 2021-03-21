package com.pricerecommenders.airbnbpricerecommendation.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {


    private String token;

    private String type = "Bearer";

    public JwtResponse(String token) {

        this.token = token;
    }

}
