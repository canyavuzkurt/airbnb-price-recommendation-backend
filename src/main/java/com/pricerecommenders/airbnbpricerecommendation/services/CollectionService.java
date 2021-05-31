package com.pricerecommenders.airbnbpricerecommendation.services;

import com.pricerecommenders.airbnbpricerecommendation.controllers.RecommendationFilter;
import com.pricerecommenders.airbnbpricerecommendation.exceptions.html.BadRequestException;
import com.pricerecommenders.airbnbpricerecommendation.model.Collection;
import com.pricerecommenders.airbnbpricerecommendation.model.Recommendation;
import com.pricerecommenders.airbnbpricerecommendation.model.User;
import com.pricerecommenders.airbnbpricerecommendation.payload.request.AddCollectionRequest;
import com.pricerecommenders.airbnbpricerecommendation.payload.request.AddToCollectionRequest;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.MessageResponse;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.RecommendationResponse;
import com.pricerecommenders.airbnbpricerecommendation.repos.BaseRepository;
import com.pricerecommenders.airbnbpricerecommendation.repos.CollectionRepo;
import com.pricerecommenders.airbnbpricerecommendation.specifications.RecommendationSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<RecommendationResponse> getRecommendationsOfCollection(Long id, RecommendationFilter filter) {

        User user = userService.getCurrentUser();
        Collection collection = findById(id);
        if (collection.getUser() != user)
            throw new BadRequestException("This collection is not yours.");

        Specification<Recommendation> spec =
                RecommendationSpec.filter(filter)
                        .and(RecommendationSpec.byCollectionId(id));

        return recommendationService.findAll(spec).stream()
                .sorted(Comparator.comparing(Recommendation::getCreatedAt).reversed())
                .map(RecommendationResponse::new).collect(Collectors.toList());
    }

    public void removeFromCollection(Long id, Long recId) {

        Collection col = findById(id);
        Recommendation rec = recommendationService.findById(recId);
        col.removeRecommendation(rec);
    }
}
