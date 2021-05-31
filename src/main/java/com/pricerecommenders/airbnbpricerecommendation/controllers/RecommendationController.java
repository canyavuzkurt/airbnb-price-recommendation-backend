package com.pricerecommenders.airbnbpricerecommendation.controllers;

import com.pricerecommenders.airbnbpricerecommendation.model.Recommendation;
import com.pricerecommenders.airbnbpricerecommendation.model.User;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.MessageResponse;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.RecommendationResponse;
import com.pricerecommenders.airbnbpricerecommendation.services.RecommendationService;
import com.pricerecommenders.airbnbpricerecommendation.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Recommendation Operations")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController extends BaseController<Recommendation>{

    private final RecommendationService recService;
    private final UserService userService;

    public RecommendationController(RecommendationService service, UserService userService) {

        super(service);
        this.recService = service;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Recommendation getRecommendation(@PathVariable("id") Long id) {

        return getEntity(id);
    }

    @PostMapping("")
    public MessageResponse addRecommendation(@Valid @RequestBody Recommendation rec) {

        return addEntity(rec);
    }

    @PutMapping("")
    public MessageResponse putRecommendation(@Valid @RequestBody Recommendation rec) {

        return putEntity(rec);
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteRecommendation(@PathVariable("id") Long id) {

        return deleteEntity(id);
    }

    @GetMapping("/my-history")
    public List<RecommendationResponse> getHistory(RecommendationFilter filter,
            @RequestParam(value = "search", required = false) String search) {

        return recService.getMyHistory(filter, search);
    }

    @GetMapping("/my-history/not-in-collection")
    public List<RecommendationResponse> getHistoryNotIncollection(RecommendationFilter filter,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "collectionId") Long collectionId) {

        return recService.getMyHistroyNotInCollection(filter, search, collectionId);
    }

    @PostMapping("/add-to-history")
    public MessageResponse addToHistory(@RequestBody Recommendation recommendation) {

        User user = userService.getCurrentUser();
        recommendation.setUser(user);
        recService.save(recommendation);

        return new MessageResponse("Added to history successfully");
    }
}
