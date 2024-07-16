package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.models.Itinerary;
import com.launchtrip.launchtrip.models.data.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:5173")
public class ItineraryController {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @GetMapping
    public List<Itinerary> getAllLocations(){
        return itineraryRepository.findAll();
    }

    @PostMapping("/delete")
    public void deleteLocation(@RequestParam Long locationId) {
        itineraryRepository.deleteById(locationId);
    }

    @PostMapping("/new")
    public Itinerary createLocation(@RequestParam String name, @RequestParam Boolean visited) {
        Itinerary newLocation = new Itinerary();
        newLocation.setName(name);
        newLocation.setVisited(visited);
        return itineraryRepository.save(newLocation);
    }
}
