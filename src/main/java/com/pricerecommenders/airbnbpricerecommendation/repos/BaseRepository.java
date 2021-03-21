package com.pricerecommenders.airbnbpricerecommendation.repos;

import com.pricerecommenders.airbnbpricerecommendation.model.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends ModelRepository<T, Long> {}
