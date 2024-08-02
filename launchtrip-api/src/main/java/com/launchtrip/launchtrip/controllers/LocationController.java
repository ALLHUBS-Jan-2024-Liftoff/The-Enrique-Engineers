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

    @GetMapping("/search")
    public List<Location> searchLocations(@RequestParam String searchQuery) {
        try {
            return searchService.searchLocationsFromQuery(searchQuery);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/getStoredLocations")
    public List<Location> getStoredLocations() {
        return locationService.getAllStoredLocations();
    }

    @GetMapping("/downloadLocationsFromGeoapify")
    public List<Location> downloadLocationsFromGeoapify(@RequestParam String searchQuery) {
        String cityName = locationService.downloadLocationsFromGeoapify(searchQuery);
        return locationService.getLocationsByCityName(cityName);
    }

    @PostMapping("/toggleVisited")
    public void toggleAsVisited(@RequestParam Long locationId) {
        System.out.println("Toggling Visited For: " + locationId);
        Location location = locationService.getLocationViaId(locationId);
        locationService.toggleLocationVisited(location);
    }

}
