package com.pricerecommenders.airbnbpricerecommendation.controllers;

import com.pricerecommenders.airbnbpricerecommendation.model.User;
import com.pricerecommenders.airbnbpricerecommendation.payload.request.LoginRequest;
import com.pricerecommenders.airbnbpricerecommendation.payload.response.JwtResponse;
import com.pricerecommenders.airbnbpricerecommendation.security.jwt.JwtUtils;
import com.pricerecommenders.airbnbpricerecommendation.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Authorization Operations")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder encoder;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtils jwtUtils,
            PasswordEncoder encoder) {

        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public JwtResponse authenticate(@Valid @RequestBody LoginRequest request) {

        User user = userService.findByApiToken(request.getApiToken());
        if (user == null) {

            user = userService.save(new User(request.getFName(), request.getLName(), request.getApiToken()));
        }

        String jwt = jwtUtils.generateJwtToken(user);

        return new JwtResponse(jwt);
    }
}
