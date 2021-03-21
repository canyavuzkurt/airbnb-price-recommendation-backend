package com.pricerecommenders.airbnbpricerecommendation.controllers;

import com.pricerecommenders.airbnbpricerecommendation.model.Recommendation;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.MessageResponse;
import com.pricerecommenders.airbnbpricerecommendation.services.RecommendationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Recommendation Operations")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/recommendatiosn")
public class RecommendationController extends BaseController<Recommendation>{

    private final RecommendationService recService;

    public RecommendationController(RecommendationService service) {

        super(service);
        this.recService = service;
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
}
