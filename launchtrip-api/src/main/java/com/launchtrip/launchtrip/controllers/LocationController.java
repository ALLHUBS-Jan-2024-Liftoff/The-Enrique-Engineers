package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.models.Location;
import com.launchtrip.launchtrip.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:5173")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/getStoredLocations")
    public List<Location> getStoredLocations() {
        return locationService.getAllStoredLocations();
    }

    @GetMapping("/downloadLocationsFromGeoapify")
    public void downloadLocationsFromGeoapify() {
        locationService.downloadLocationsFromGeoapify();
    }

    @PostMapping("/toggleVisited")
    public void deleteLocation(@RequestParam Long locationId) {
        System.out.println("Toggling Visited For: " + locationId);
        Location location = locationService.getLocationViaId(locationId);
        locationService.toggleLocationVisited(location);
    }
}
