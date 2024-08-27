package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.models.Itinerary;
import com.launchtrip.launchtrip.models.ItineraryLocation;
import com.launchtrip.launchtrip.models.Location;
import com.launchtrip.launchtrip.models.Review;
import com.launchtrip.launchtrip.models.data.ItineraryLocationRepository;
import com.launchtrip.launchtrip.models.data.ItineraryRepository;
import com.launchtrip.launchtrip.models.data.LocationRepository;
import com.launchtrip.launchtrip.services.ItineraryService;
import com.launchtrip.launchtrip.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itineraries")
@CrossOrigin(origins = "http://localhost:5173")
public class ItineraryController {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ItineraryService itineraryService;

    @Autowired
    private ItineraryLocationRepository itineraryLocationRepository;

    private LocationController locationController = new LocationController();

    @GetMapping()
    public List<Itinerary> getAllItineraries(){
        System.out.println("getAllItineraries is being called");
        List<Itinerary> itineraries = itineraryRepository.findAll();
        return itineraries;
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

        itineraryToEdit.addLocation(locationToAdd);
        itineraryRepository.save(itineraryToEdit);

        ItineraryLocation itineraryLocation = new ItineraryLocation(itineraryId, locationId);
        itineraryLocationRepository.save(itineraryLocation);

        assignPriorityFromId(itineraryLocation);
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

    @GetMapping("/{itineraryId}/getReviews")
    public ResponseEntity<List<Review>> getReviewsForItinerary(@PathVariable Long itineraryId) {
        Optional<Itinerary> optionalItinerary = itineraryRepository.findById(itineraryId);

        if (optionalItinerary.isPresent()) {
            Itinerary itinerary = optionalItinerary.get();
            List<Review> reviews = itinerary.getReviews();
            return ResponseEntity.ok(reviews);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>()); // Return an empty list or handle as preferred
        }
    }

    @PostMapping("/{itineraryId}/setReviews")
    public ResponseEntity<?> addReview(@PathVariable Long itineraryId, @RequestBody Review review) {
        reviewService.addReviewToItinerary(itineraryId, review);
        return ResponseEntity.ok("Review added successfully!");
    }

    @PostMapping("/toggleItineraryVisited")
    public void toggleItineraryVisited(@RequestParam Long itineraryId) {
        System.out.println("Toggling Visited For: " + itineraryId);
        Itinerary itinerary = itineraryService.getItineraryViaId(itineraryId);
        itineraryService.toggleItineraryVisited(itinerary);
    }

    private void assignPriorityFromId(ItineraryLocation itineraryLocation) {
        List<ItineraryLocation> allItineraryLocations = itineraryLocationRepository.findAll();

        for (ItineraryLocation itineraryLoc : allItineraryLocations) {
            if (itineraryLoc.getItineraryId().equals(itineraryLocation.getItineraryId())) {
                if (itineraryLoc.getLocationId().equals(itineraryLocation.getLocationId())) {
                    itineraryLoc.setPriority(itineraryLoc.getId());
                    itineraryLocationRepository.save(itineraryLoc);
                    break;
                }
            }
        }
    }

}
