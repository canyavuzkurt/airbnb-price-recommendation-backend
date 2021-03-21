package com.pricerecommenders.airbnbpricerecommendation.repos;

import com.pricerecommenders.airbnbpricerecommendation.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends BaseRepository<User>{

    User findByApiToken(String apiToken);
}
