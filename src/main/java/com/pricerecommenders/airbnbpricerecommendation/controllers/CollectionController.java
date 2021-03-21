package com.pricerecommenders.airbnbpricerecommendation.controllers;

import com.pricerecommenders.airbnbpricerecommendation.model.Collection;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.MessageResponse;
import com.pricerecommenders.airbnbpricerecommendation.services.CollectionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Collection Operations")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/collections")
public class CollectionController extends BaseController<Collection>{

    private final CollectionService colService;

    public CollectionController(CollectionService service) {

        super(service);
        this.colService = service;
    }

    @GetMapping("/{id}")
    public Collection getCollection(@PathVariable("id") Long id) {

        return getEntity(id);
    }

    @PostMapping("")
    public MessageResponse addCollection(@Valid @RequestBody Collection col) {

        return addEntity(col);
    }

    @PutMapping("")
    public MessageResponse putCollection(@Valid @RequestBody Collection col) {

        return putEntity(col);
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteCollection(@PathVariable("id") Long id) {

        return deleteEntity(id);
    }
}
