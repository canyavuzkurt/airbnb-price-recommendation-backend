package com.pricerecommenders.airbnbpricerecommendation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Import({ BeanValidatorPluginsConfiguration.class, SpringDataRestConfiguration.class })
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Creates the Swagger configuration bean.
     *
     * @return docket bean
     */
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2).groupName("PriceRecommenders").select()
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo("Airbnb Price Recommendation API",
                        "Backend API for the Airbnb Price Recommendation Project by Can Yavuzkurt, Caner Tangüler and Kemal Türk.\n"
                                + "This api adheres to the RESTfull properties."
                                + "##Editing an Entity"
                                + "User put requests, meaning that every info about the entity should be sent to the endpoint whether it was changed or not."
                                ));
    }

    /**
     * Creates an object containing API information including author name,
     * email, version, license, etc.
     *
     * @param title API title
     * @param description API description
     * @return API information
     */
    private ApiInfo apiInfo(String title, String description) {

        return new ApiInfoBuilder().title(title).description(description).version("1.0.0-Snapshot").build();
    }
}