package com.company.bookservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public OpenAPI foo(){
        return new OpenAPI();
    }
}
