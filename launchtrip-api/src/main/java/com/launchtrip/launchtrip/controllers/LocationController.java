package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.models.Itinerary;
import com.launchtrip.launchtrip.models.ItineraryLocation;
import com.launchtrip.launchtrip.models.Location;
import com.launchtrip.launchtrip.models.data.ItineraryLocationRepository;
import com.launchtrip.launchtrip.models.data.ItineraryRepository;
import com.launchtrip.launchtrip.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:5173")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private ItineraryLocationRepository itineraryLocationRepository;

    //@Autowired
    //private SearchService searchService;

    @GetMapping("/getStoredLocations")
    public List<Location> getStoredLocations() {
        return locationService.getAllStoredLocations();
    }

    @GetMapping("/downloadLocationsFromGeoapify")
    public List<Location> downloadLocationsFromGeoapify(@RequestParam String searchQuery, @RequestParam String categories) {
        String cityGroup = locationService.downloadLocationsFromGeoapify(searchQuery);
        List<Location> allCityLocations = locationService.getLocationsByCity(cityGroup);
        List<Location> filteredCityLocations = locationService.filterLocationsByType(allCityLocations, categories);
        return filteredCityLocations;
    }

    /*
    @PostMapping("/toggleVisited")
    public void toggleAsVisited(@RequestParam Long locationId) {
        System.out.println("Toggling Visited For: " + locationId);
        Location location = locationService.getLocationViaId(locationId);
        locationService.toggleLocationVisited(location);
    }
     */
    @PostMapping("/toggleVisited")
    public void toggleVisited(@RequestParam Long itineraryId, @RequestParam Long locationId) {
        System.out.println("Toggling Visited For: " + locationId + " On Itinerary: " + itineraryId);
        List<ItineraryLocation> allItineraryLocations = itineraryLocationRepository.findAll();
        Boolean itineraryLocationExists = false;
        for (ItineraryLocation itineraryLocation : allItineraryLocations) {
            if (itineraryLocation.getItineraryId().equals(itineraryId) &&
                itineraryLocation.getLocationId().equals(locationId)) {
                itineraryLocationExists = true;
                if (itineraryLocation.getVisitedLocation() == true) {
                    itineraryLocation.setVisitedLocation(false);
                }
                else {
                    itineraryLocation.setVisitedLocation(true);
                }
                itineraryLocationRepository.save(itineraryLocation);
                break;
            }
        }
        if (itineraryLocationExists == false) {
            ItineraryLocation itineraryLocation = new ItineraryLocation(itineraryId, locationId);
            itineraryLocation.setVisitedLocation(true);
            itineraryLocationRepository.save(itineraryLocation);
        }
    }

    @GetMapping("/getIsLocationVisited/{itineraryId}/{locationId}")
    public Boolean isLocationVisited(@PathVariable Long itineraryId, @PathVariable Long locationId) {
        List<ItineraryLocation> allItineraryLocations = itineraryLocationRepository.findAll();

        for (ItineraryLocation itineraryLocation : allItineraryLocations) {
            System.out.println("Itinerary: " + itineraryLocation.getItineraryId());
            System.out.println("Location: " + itineraryLocation.getLocationId());
            System.out.println("Visited: " + itineraryLocation.getVisitedLocation());
            System.out.println();
        }

        for (ItineraryLocation itineraryLocation : allItineraryLocations) {

            if (itineraryLocation.getItineraryId().equals(itineraryId) &&
                itineraryLocation.getLocationId().equals(locationId))
            {
                return itineraryLocation.getVisitedLocation();
            }
        }
        return false;
    }
}
