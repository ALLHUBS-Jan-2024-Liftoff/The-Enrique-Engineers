package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.services.LocationOfTheDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LocationOfTheDayController {

    @Autowired
    private LocationOfTheDayService locationOfTheDayService;

    @GetMapping("/api/location-of-the-day")
    public String getLocationOfTheDay() {
        try {
            String randomLocationName = locationOfTheDayService.getRandomLocationName();
            return "Location of the Day: " + randomLocationName;
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
