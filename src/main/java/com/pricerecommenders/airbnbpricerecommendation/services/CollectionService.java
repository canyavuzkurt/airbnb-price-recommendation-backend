package com.pricerecommenders.airbnbpricerecommendation.services;

import com.pricerecommenders.airbnbpricerecommendation.model.Collection;
import com.pricerecommenders.airbnbpricerecommendation.model.Recommendation;
import com.pricerecommenders.airbnbpricerecommendation.payload.request.AddCollectionRequest;
import com.pricerecommenders.airbnbpricerecommendation.payload.request.AddToCollectionRequest;
import com.pricerecommenders.airbnbpricerecommendation.repos.BaseRepository;
import com.pricerecommenders.airbnbpricerecommendation.repos.CollectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService extends BaseService<Collection>{

    private final CollectionRepo repo;
    private final RecommendationService recommendationService;
    private final UserService userService;

    public CollectionService(BaseRepository<Collection> repository, CollectionRepo repo,
            RecommendationService recommendationService, UserService userService) {

        super("Collection", repository);
        this.repo = repo;
        this.recommendationService = recommendationService;
        this.userService = userService;
    }

    public void addToCollection(AddToCollectionRequest request) {

        Collection collection = findById(request.getCollectionId());
        Recommendation recommendation = recommendationService.findById(request.getRecommendationId());

        collection.getRecommendations().add(recommendation);
        save(collection);
    }

    public void addCollection(AddCollectionRequest request) {

        Collection collection = new Collection();
        collection.setName(request.getName());
        collection.setUser(userService.getCurrentUser());
        save(collection);
    }
}
