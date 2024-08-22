package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.services.LocationOfTheDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class LocationOfTheDayController {

    @Autowired
    private LocationOfTheDayService locationOfTheDayService;

    @GetMapping("/api/location-of-the-day")
    public String getLocationOfTheDay() {
        try {
            String randomLocationName = locationOfTheDayService.getRandomLocationName();
            return randomLocationName;
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}