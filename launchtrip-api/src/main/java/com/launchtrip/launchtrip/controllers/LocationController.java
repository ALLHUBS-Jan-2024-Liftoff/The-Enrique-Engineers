package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.models.Location;
import com.launchtrip.launchtrip.models.data.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:5173")
public class LocationController {
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @PostMapping("/new")
    public Location createLocation(@RequestParam String name, @RequestParam String address) {
        Location newLocation = new Location();
        newLocation.setName(name);
        newLocation.setAddress(address);
        return locationRepository.save(newLocation);
    }

    @PostMapping("/delete")
    public void deleteLocation(@RequestParam Long locationId) {
        System.out.println("Removing location: " + locationId);
        locationRepository.deleteById(locationId);
    }
}
