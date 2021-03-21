package com.pricerecommenders.airbnbpricerecommendation.repos;

import com.pricerecommenders.airbnbpricerecommendation.model.Recommendation;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepo extends BaseRepository<Recommendation>{}
