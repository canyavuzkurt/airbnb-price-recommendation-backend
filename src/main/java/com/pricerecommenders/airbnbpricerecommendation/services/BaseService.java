package com.pricerecommenders.airbnbpricerecommendation.services;


import com.pricerecommenders.airbnbpricerecommendation.model.BaseEntity;
import com.pricerecommenders.airbnbpricerecommendation.repos.BaseRepository;

public class BaseService<T extends BaseEntity> extends
        ModelService<T, Long> {

    private BaseRepository<T> repository;

    public BaseService(String resourceName, BaseRepository<T> repository) {

        super(resourceName, repository);
        this.repository = repository;
    }
}
