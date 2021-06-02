package com.pricerecommenders.airbnbpricerecommendation.payload.response;

import com.pricerecommenders.airbnbpricerecommendation.model.Recommendation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationResponse {

    private Long id;

    private String neighbourhood;

    private String address;

    private Double latitude;

    private Double longitude;

    private String propertyType;

    private String roomType;

    private Long accomodates;

    private Long beds;

    private List<String> amenities;

    private Long price;

    private Long minimumNights;

    private Long maximumNights;

    private Boolean instantBookable;

    public RecommendationResponse(Recommendation rec) {

        setId(rec.getId());
        setNeighbourhood(rec.getNeighbourhood());
        setAddress(rec.getAddress());
        setLatitude(rec.getLatitude());
        setLongitude(rec.getLongitude());
        setPropertyType(rec.getPropertyType());
        setRoomType(rec.getRoomType());
        setAccomodates(rec.getAccommodates());
        setBeds(rec.getBeds());
        setAmenities(rec.getAmenities());
        setPrice(rec.getPrice());
        setMinimumNights(rec.getMinimumNights());
        setMaximumNights(rec.getMaximumNights());
        setInstantBookable(rec.getInstantBookable());
    }
}
