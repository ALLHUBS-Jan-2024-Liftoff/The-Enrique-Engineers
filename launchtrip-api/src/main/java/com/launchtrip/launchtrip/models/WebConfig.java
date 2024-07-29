package com.launchtrip.launchtrip.models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Adjust the mapping to cover your endpoints
                .allowedOrigins("http://localhost:5173") // Your frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*") // Allow all headers (or specify required headers)
                .allowCredentials(true); // Allow credentials such as cookies
}}
