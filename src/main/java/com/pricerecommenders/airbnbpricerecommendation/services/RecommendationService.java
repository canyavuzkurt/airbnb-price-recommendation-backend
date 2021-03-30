package com.pricerecommenders.airbnbpricerecommendation.services;

import com.pricerecommenders.airbnbpricerecommendation.controllers.RecommendationFilter;
import com.pricerecommenders.airbnbpricerecommendation.model.Recommendation;
import com.pricerecommenders.airbnbpricerecommendation.model.User;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.RecommendationResponse;
import com.pricerecommenders.airbnbpricerecommendation.repos.BaseRepository;
import com.pricerecommenders.airbnbpricerecommendation.repos.RecommendationRepo;
import com.pricerecommenders.airbnbpricerecommendation.specifications.RecommendationSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService extends BaseService<Recommendation>{

    private final RecommendationRepo repo;

    private final UserService userService;

    public RecommendationService(BaseRepository<Recommendation> repository, RecommendationRepo repo,
            UserService userService) {

        super("Recommendation", repository);
        this.repo = repo;
        this.userService = userService;
    }

    public List<RecommendationResponse> getMyHistory(RecommendationFilter filter) {

        User user = userService.getCurrentUser();
        Long id = user.getId();

        Specification<Recommendation> spec =
                RecommendationSpec.filter(filter)
                .and(RecommendationSpec.byUserId(id));

        return findAll(spec).stream()
                .sorted(Comparator.comparing(Recommendation::getCreatedAt).reversed())
                .map(RecommendationResponse::new).collect(Collectors.toList());
    }


}
