package com.pricerecommenders.airbnbpricerecommendation.services;

import com.pricerecommenders.airbnbpricerecommendation.exceptions.html.BadRequestException;
import com.pricerecommenders.airbnbpricerecommendation.model.User;
import com.pricerecommenders.airbnbpricerecommendation.repos.BaseRepository;
import com.pricerecommenders.airbnbpricerecommendation.repos.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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


    public User getCurrentUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        User user = repo.findByApiToken(username);
        if (user == null)
            throw new BadRequestException("Not logged in or no user.");

        return user;

    }

}
