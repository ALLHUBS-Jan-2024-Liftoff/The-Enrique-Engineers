package com.launchtrip.launchtrip.models;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashSet;
import java.util.Set;

@Component
public class Interceptors implements HandlerInterceptor {

    private static final Set<String> WHITELISTED_PATHS = new HashSet<>();

    static {
        WHITELISTED_PATHS.add("/api/Auth/login");  // Add paths to whitelist
        WHITELISTED_PATHS.add("/api/Auth/register");
        // Add more paths as needed
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        // Skip processing for whitelisted paths
        if (WHITELISTED_PATHS.contains(requestURI)) {
            return true;
        }

        // Your logic for non-whitelisted paths
        System.out.println("Intercepted request URI: " + requestURI);

        // Example: Check if the user is authenticated
        // If not authenticated, you might want to reject the request
        // If authenticated, return true to continue

        return true; // Return true to continue with the request
    }
}