package com.pricerecommenders.airbnbpricerecommendation.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@ApiIgnore
@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String swaggerRedir(Principal principal) {

        return "redirect:/swagger-ui/";
    }
}
