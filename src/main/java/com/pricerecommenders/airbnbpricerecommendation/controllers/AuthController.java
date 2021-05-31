package com.pricerecommenders.airbnbpricerecommendation.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

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

    @PostMapping("/authenticate")
    public JwtResponse exchange(@Autowired NetHttpTransport transport, @Autowired GsonFactory factory, @Valid @RequestBody LoginRequest request) throws
            GeneralSecurityException, IOException, IllegalAccessException {
        // Create verifier
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, factory)
                .setAudience(Collections.singletonList("870980682398-cnisdnh8upfa5isvjnbsitqnvo4goavs.apps.googleusercontent.com"))
                .build();
        // Verify it
        GoogleIdToken idToken = verifier.verify(request.getApiToken());
        if (idToken == null) {
            throw new IllegalAccessException("Invalid id_token");
        }
            // Access payload
        String userId = idToken.getPayload().getUserId();
        User user = userService.findByApiToken(userId);

        if (user == null) {

            user = userService.save(new User(request.getFName(), request.getLName(), request.getApiToken()));
        }


        String jwt = jwtUtils.generateJwtToken(user);

        return new JwtResponse(jwt);
    }

    public String getTokenFromRequest(HttpServletRequest request) throws IllegalAccessException {
        String token = request.getHeader("Authorization");
        String[] parts = token.split(" ");
        if (parts.length != 2 || !parts[0].contains("Bearer")) {
            throw new IllegalAccessException("Authorization Bearer format invalid. <Bearer {token}>");
        }
        return parts[1];
    }
}
