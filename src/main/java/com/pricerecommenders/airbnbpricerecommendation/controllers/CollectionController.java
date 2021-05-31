package com.pricerecommenders.airbnbpricerecommendation.controllers;

import com.pricerecommenders.airbnbpricerecommendation.model.Collection;
import com.pricerecommenders.airbnbpricerecommendation.model.User;
import com.pricerecommenders.airbnbpricerecommendation.payload.request.AddCollectionRequest;
import com.pricerecommenders.airbnbpricerecommendation.payload.request.AddToCollectionRequest;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.CollectionResponse;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.MessageResponse;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.RecommendationResponse;
import com.pricerecommenders.airbnbpricerecommendation.services.CollectionService;
import com.pricerecommenders.airbnbpricerecommendation.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Collection Operations")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/collections")
public class CollectionController extends BaseController<Collection>{

    private final CollectionService colService;
    private final UserService userService;

    public CollectionController(CollectionService service, UserService userService) {

        super(service);
        this.colService = service;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public CollectionResponse getCollection(@PathVariable("id") Long id) {

        return new CollectionResponse(getEntity(id));
    }

    @GetMapping("/my-collections")
    public List<CollectionResponse> getMyCollections() {

        User user = userService.getCurrentUser();
        return user.getCollections().stream().map(CollectionResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}/recommendations")
    public List<RecommendationResponse> getRecommendationsOfCollection(@PathVariable("id") Long id, RecommendationFilter filter) {

        return colService.getRecommendationsOfCollection(id, filter);
    }

    @PostMapping("")
    public MessageResponse addCollection(@Valid @RequestBody AddCollectionRequest request) {

        colService.addCollection(request);
        return new MessageResponse("Collection added successfully.");
    }

    @PutMapping("")
    public MessageResponse putCollection(@Valid @RequestBody Collection col) {

        return putEntity(col);
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteCollection(@PathVariable("id") Long id) {

        return deleteEntity(id);
    }

    @DeleteMapping("/{id}/recommendation/{recommendationId}")
    public MessageResponse deleteRecommendationFromHistory(
            @PathVariable("id") Long id,
            @PathVariable("recommendationId") Long recId) {

        colService.removeFromCollection(id, recId);
        return new MessageResponse("Recommendation removed from the collection.");
    }

    @PostMapping("/add/")
    public MessageResponse addToCollection(@Valid @RequestBody AddToCollectionRequest request) {

        colService.addToCollection(request);
        return new MessageResponse("Added to collection successfully.");
    }
}
