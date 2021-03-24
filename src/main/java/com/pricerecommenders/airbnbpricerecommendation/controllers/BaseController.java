package com.pricerecommenders.airbnbpricerecommendation.controllers;

import com.pricerecommenders.airbnbpricerecommendation.model.BaseEntity;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.MessageResponse;
import com.pricerecommenders.airbnbpricerecommendation.services.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
public class BaseController<T extends BaseEntity> {

    private BaseService<T> service;

    public T getEntity(Long id) {

        return service.findById(id);
    }

    public MessageResponse addEntity(T entity) {

        entity.setId(null);
        service.save(entity);
        return new MessageResponse(service.getResourceName() + " added successfully.");
    }

    public MessageResponse putEntity(T entity) {

        service.save(entity);
        return new MessageResponse(service.getResourceName() + " edited successfully.");
    }

    public MessageResponse deleteEntity(Long id) {

        service.deleteById(id);
        return new MessageResponse(service.getResourceName() + " deleted successfully.");
    }
}
