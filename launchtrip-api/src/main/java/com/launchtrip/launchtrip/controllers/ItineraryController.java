package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.models.Itinerary;
import com.launchtrip.launchtrip.models.Location;
import com.launchtrip.launchtrip.models.data.ItineraryRepository;
import com.launchtrip.launchtrip.models.data.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itineraries")
@CrossOrigin(origins = "http://localhost:5173")
public class ItineraryController {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping()
    public List<Itinerary> getAllItineraries(){
        System.out.println("getAllItineraries is being called");
        return itineraryRepository.findAll();
    }

    @GetMapping("/getAddedLocations/{itineraryId}")
    public List<Location> getAddedLocationsFromItinerary(@PathVariable Long itineraryId) {
        Itinerary itineraryToView = itineraryRepository.getReferenceById(itineraryId);
        return itineraryToView.getLocations();
    }

    @GetMapping("/getItineraryName/{itineraryId}")
    public String getItineraryName(@PathVariable Long itineraryId) {
        Itinerary itineraryToView = itineraryRepository.getReferenceById(itineraryId);
        return itineraryToView.getName();
    }

    @PostMapping("/new")
    public Itinerary createItinerary(@RequestParam String name, @RequestParam Boolean visited) {
        Itinerary newLocation = new Itinerary();
        newLocation.setName(name);
        newLocation.setVisited(visited);
        return itineraryRepository.save(newLocation);
    }

    @PostMapping("/addLocationToItinerary")
    public void addLocationToItinerary(@RequestParam Long itineraryId, @RequestParam Long locationId) {
        Itinerary itineraryToEdit = itineraryRepository.getReferenceById(itineraryId);
        Location locationToAdd = locationRepository.getReferenceById(locationId);

        itineraryToEdit.addLocation(locationToAdd);;
        itineraryRepository.save(itineraryToEdit);
    }

    @PostMapping("/removeLocationFromItinerary")
    public void removeLocationFromItinerary(@RequestParam Long itineraryId, @RequestParam Long locationId) {
        Itinerary itineraryToEdit = itineraryRepository.getReferenceById(itineraryId);
        Location locationToRemove = locationRepository.getReferenceById(locationId);

        itineraryToEdit.removeLocation(locationToRemove);
        itineraryRepository.save(itineraryToEdit);
    }

    @PostMapping("/delete")
    public void deleteItinerary(@RequestParam Long itineraryId) {
        itineraryRepository.deleteById(itineraryId);
    }
}
