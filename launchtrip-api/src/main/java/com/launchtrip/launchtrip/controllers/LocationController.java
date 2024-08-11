package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.models.Location;
import com.launchtrip.launchtrip.services.LocationService;
import com.launchtrip.launchtrip.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:5173")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private SearchService searchService;

    @GetMapping("/getStoredLocations")
    public List<Location> getStoredLocations() {
        return locationService.getAllStoredLocations();
    }

    @GetMapping("/downloadLocationsFromGeoapify")
    public List<Location> downloadLocationsFromGeoapify(@RequestParam String searchQuery, @RequestParam String categories) {
        String cityGroup = locationService.downloadLocationsFromGeoapify(searchQuery);
        List<Location> allCityLocations = locationService.getLocationsByCity(cityGroup);
        return allCityLocations;
    }

    @PostMapping("/toggleVisited")
    public void toggleAsVisited(@RequestParam Long locationId) {
        System.out.println("Toggling Visited For: " + locationId);
        Location location = locationService.getLocationViaId(locationId);
        locationService.toggleLocationVisited(location);
    }

}
