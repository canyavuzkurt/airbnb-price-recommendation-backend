package com.pricerecommenders.airbnbpricerecommendation.specifications;

import com.pricerecommenders.airbnbpricerecommendation.controllers.RecommendationFilter;
import com.pricerecommenders.airbnbpricerecommendation.model.Recommendation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class RecommendationSpec {


    public static Specification<Recommendation> likeNeighbourhood(String neighbourhood) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get("neighbourhood"), neighbourhood);
    }

    public static Specification<Recommendation> byLatitude(Double latitude) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("latitude"), latitude);
    }

    public static Specification<Recommendation> byLongitude(Double longitude) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("longitude"), longitude);
    }

    public static Specification<Recommendation> likeRoomType(String roomType) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get("roomType"), roomType);
    }

    public static Specification<Recommendation> byPropertyType(String propertyType) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get("propertyType"), propertyType);
    }

    public static Specification<Recommendation> byAccommodates(Long accommodates) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("accommodates"), accommodates);
    }

    public static Specification<Recommendation> likeBeds(Long beds) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("beds"), beds);
    }

    public static Specification<Recommendation> hasAmenities(List<String> amenities) {

        return (root, query, criteriaBuilder) -> {

            root.join("amenities", JoinType.LEFT);

            Specification<Recommendation> spec = Specification.where(null);
            List<Predicate> predicates = new ArrayList<>();
            for (String amenity : amenities) {

                predicates.add(criteriaBuilder.like(root.get("amenities"), amenity));
            }
            return criteriaBuilder.and((Predicate[]) predicates.toArray());
        };
    }

    public static Specification<Recommendation> byPrice(Long price) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("price"), price);
    }

    public static Specification<Recommendation> byMinimumNights(Long minimumNights) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("minimumNights"), minimumNights);
    }

    public static Specification<Recommendation> byMaximumNights(Long maximumNights) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("maximumNights"), maximumNights);
    }

    public static Specification<Recommendation> byInstantBookable(Boolean instantBookable) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("instantBookable"), instantBookable);
    }


    public static Specification<Recommendation> filter(RecommendationFilter filter) {

        Specification<Recommendation> spec = Specification.where(null);

        return spec
                .or(byAccommodates(filter.getAccommodatesFilter()))
                .or(byInstantBookable(filter.getInstantBookableFilter()))
                .or(byLatitude(filter.getLatitudeFilter()))
                .or(byLongitude(filter.getLongitudeFilter()))
                .or(byMaximumNights(filter.getMaximumNightsFilter()))
                .or(byMinimumNights(filter.getMinimumNightsFilter()))
                .or(byPrice(filter.getPriceFilter()))
                .or(byPropertyType(filter.getPropertyTypeFilter()))
                .or(likeBeds(filter.getBedsFilter()))
                .or(likeNeighbourhood(filter.getNeighbourhoodFilter()))
                .or(likeRoomType(filter.getRoomTypeFilter()));
        //TODO Amenities filter missing
    }

    public static Specification<Recommendation> byUserId(Long userId) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("user").get("id"), userId);
    }

}
