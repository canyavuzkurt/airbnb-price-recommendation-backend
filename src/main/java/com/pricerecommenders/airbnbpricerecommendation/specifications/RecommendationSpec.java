package com.pricerecommenders.airbnbpricerecommendation.specifications;

import com.pricerecommenders.airbnbpricerecommendation.model.Recommendation;
import org.springframework.data.jpa.domain.Specification;

public class RecommendationSpec {


    public static Specification<Recommendation> likeNeighbourhood(String neighbourhood) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get("neighbourhood"), neighbourhood);
    }

    public static Specification<Recommendation> byLatitude(Long latitude) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("neighbourhood"), latitude);
    }

    public static Specification<Recommendation> byLongitude(Long longitude) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("neighbourhood"), longitude);
    }

    public static Specification<Recommendation> likeRoomType(String roomType) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get("neighbourhood"), roomType);
    }

    public static Specification<Recommendation> byaccommodates(String propertyType) {

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get("neighbourhood"), propertyType);
    }

//    public static Specification<Recommendation> likePropertyType(String propertyType) {
//
//        return (root, query, criteriaBuilder) -> criteriaBuilder
//                .like(root.get("neighbourhood"), propertyType);
//    }
//
//    public static Specification<Recommendation> likePropertyType(String propertyType) {
//
//        return (root, query, criteriaBuilder) -> criteriaBuilder
//                .like(root.get("neighbourhood"), propertyType);
//    }
//
//    public static Specification<Recommendation> likePropertyType(String propertyType) {
//
//        return (root, query, criteriaBuilder) -> criteriaBuilder
//                .like(root.get("neighbourhood"), propertyType);
//    }
//
//    public static Specification<Recommendation> likePropertyType(String propertyType) {
//
//        return (root, query, criteriaBuilder) -> criteriaBuilder
//                .like(root.get("neighbourhood"), propertyType);
//    }

}
