package com.pricerecommenders.airbnbpricerecommendation.specifications;

import com.pricerecommenders.airbnbpricerecommendation.controllers.RecommendationFilter;
import com.pricerecommenders.airbnbpricerecommendation.model.Recommendation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
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

//            Join<Object, Object> amenities1 = root.join("amenities", JoinType.INNER);
//
//            Specification<Recommendation> spec = Specification.where(null);
            List<Predicate> predicates = new ArrayList<>();
            for (String amenity : amenities) {

                predicates.add(criteriaBuilder.isMember(amenity, root.get("amenities") ));
            }
            Predicate[] p = new Predicate[amenities.size()];
            return criteriaBuilder.and(predicates.toArray(p));
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

        if (filter.getAccommodatesFilter() != null)
            spec = spec.or(byAccommodates(filter.getAccommodatesFilter()));

        if (filter.getInstantBookableFilter() != null)
            spec = spec.or(byInstantBookable(filter.getInstantBookableFilter()));

        if (filter.getLatitudeFilter() != null)
            spec = spec.or(byLatitude(filter.getLatitudeFilter()));

        if (filter.getLongitudeFilter() != null)
            spec = spec.or(byLongitude(filter.getLongitudeFilter()));

        if (filter.getMaximumNightsFilter() != null)
            spec = spec.or(byMaximumNights(filter.getMaximumNightsFilter()));

        if (filter.getMinimumNightsFilter() != null)
            spec = spec.or(byMinimumNights(filter.getMinimumNightsFilter()));

        if (filter.getPriceFilter() != null)
            spec = spec.or(byPrice(filter.getPriceFilter()));

        if (filter.getPropertyTypeFilter() != null)
            spec = spec.or(byPropertyType(filter.getPropertyTypeFilter()));

        if (filter.getBedsFilter() != null)
            spec = spec.or(likeBeds(filter.getBedsFilter()));

        if (filter.getNeighbourhoodFilter() != null)
            spec = spec.or(likeNeighbourhood(filter.getNeighbourhoodFilter()));

        if (filter.getRoomTypeFilter() != null)
            spec = spec.or(likeRoomType(filter.getRoomTypeFilter()));

        //TODO Amenities filter missing
        if (filter.getAmenitiesFilter() != null)
            spec = spec.or(hasAmenities(filter.getAmenitiesFilter()));

        return spec;
    }

    public static Specification<Recommendation> byUserId(Long userId) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("user").get("id"), userId);
    }

    public static Specification<Recommendation> byCollectionId(Long collectionId) {

        return (root, query, criteriaBuilder) -> criteriaBuilder.isMember(collectionId, root.get("collections"));
    }

}
