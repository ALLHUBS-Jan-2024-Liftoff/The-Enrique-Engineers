package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.models.Location;
import com.launchtrip.launchtrip.services.LocationOfTheDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location-of-the-day")
@CrossOrigin(origins = "http://localhost:5173")
public class LocationOfTheDayController {

    @Autowired
    private LocationOfTheDayService locationOfTheDayService;

    @GetMapping
    public List<Location> getLocationOfTheDay() {
        return locationOfTheDayService.getLocationOfTheDay();
    }
}
