package com.pricerecommenders.airbnbpricerecommendation.services;

import com.pricerecommenders.airbnbpricerecommendation.model.Recommendation;
import com.pricerecommenders.airbnbpricerecommendation.repos.BaseRepository;
import com.pricerecommenders.airbnbpricerecommendation.repos.RecommendationRepo;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService extends BaseService<Recommendation>{

    private final RecommendationRepo repo;

    public RecommendationService(BaseRepository<Recommendation> repository, RecommendationRepo repo) {

        super("Recommendation", repository);
        this.repo = repo;
    }
}
