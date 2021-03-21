package com.pricerecommenders.airbnbpricerecommendation.services;

import com.pricerecommenders.airbnbpricerecommendation.model.User;
import com.pricerecommenders.airbnbpricerecommendation.repos.BaseRepository;
import com.pricerecommenders.airbnbpricerecommendation.repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {

    private final UserRepo repo;

    public UserService(BaseRepository<User> repository, UserRepo repo) {

        super("User", repository);
        this.repo = repo;
    }

    public User findByApiToken(String apiToken) {

        return repo.findByApiToken(apiToken);
    }
}
