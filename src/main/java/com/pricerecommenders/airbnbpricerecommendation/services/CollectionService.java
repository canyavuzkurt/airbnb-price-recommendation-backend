package com.pricerecommenders.airbnbpricerecommendation.services;

import com.pricerecommenders.airbnbpricerecommendation.model.Collection;
import com.pricerecommenders.airbnbpricerecommendation.repos.BaseRepository;
import com.pricerecommenders.airbnbpricerecommendation.repos.CollectionRepo;
import org.springframework.stereotype.Service;

@Service
public class CollectionService extends BaseService<Collection>{

    private final CollectionRepo repo;

    public CollectionService(BaseRepository<Collection> repository, CollectionRepo repo) {

        super("Collection", repository);
        this.repo = repo;
    }
}
