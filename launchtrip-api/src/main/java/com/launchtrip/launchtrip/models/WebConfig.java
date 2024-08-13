package com.launchtrip.launchtrip.models;

import com.launchtrip.launchtrip.models.Interceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private Interceptors Interceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(Interceptors)
                .addPathPatterns("/api/Auth/**")  // Apply the interceptor to the specified path
                .excludePathPatterns("/api/Auth/login", "/api/Auth/register"); // Explicitly exclude paths if needed
    }
}


