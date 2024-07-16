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
    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    @PostMapping("/delete")
    public void deleteLocation(@RequestParam Long locationId) {
        locationRepository.deleteById(locationId);
    }

    @PostMapping("/new")
    public Location createLocation(@RequestParam String name, @RequestParam Boolean visited) {
        Location newLocation = new Location();
        newLocation.setName(name);
        newLocation.setVisited(visited);
        return locationRepository.save(newLocation);
    }
}
