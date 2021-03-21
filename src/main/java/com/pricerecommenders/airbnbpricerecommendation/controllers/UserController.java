package com.pricerecommenders.airbnbpricerecommendation.controllers;

import com.pricerecommenders.airbnbpricerecommendation.model.User;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.MessageResponse;
import com.pricerecommenders.airbnbpricerecommendation.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "User Operations")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController<User> {

    private final UserService userService;

    public UserController(UserService userService) {

        super(userService);
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {

        return getEntity(id);
    }

    @PostMapping("")
    public MessageResponse addUser(@Valid @RequestBody User user) {

        return addEntity(user);
    }

    @PutMapping("")
    public MessageResponse putUser(@Valid @RequestBody User user) {

        return putEntity(user);
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteUser(@PathVariable("id") Long id) {

        return deleteEntity(id);
    }
}
