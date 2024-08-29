package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.models.Itinerary;
import com.launchtrip.launchtrip.models.ItineraryLocation;
import com.launchtrip.launchtrip.models.Location;
import com.launchtrip.launchtrip.models.data.ItineraryLocationRepository;
import com.launchtrip.launchtrip.models.data.ItineraryRepository;
import com.launchtrip.launchtrip.models.data.LocationRepository;
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
    private LocationRepository locationRepository;

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

    @PostMapping("/increaseLocationPriority")
    public void increaseLocationPriority(@RequestParam Long itineraryId, @RequestParam Long locationId) {
        List<ItineraryLocation> allItineraryLocations = itineraryLocationRepository.findAll();
        Long currentLocationPriority = 0L;
        Long revisedLocationPriority = 0L;
        for (ItineraryLocation itineraryLocation : allItineraryLocations) {
            if (itineraryLocation.getItineraryId().equals(itineraryId) &&
                itineraryLocation.getLocationId().equals(locationId)) {
                currentLocationPriority = itineraryLocation.getPriority();
                if (currentLocationPriority > 1) {
                    revisedLocationPriority = currentLocationPriority - 1;
                    itineraryLocation.setPriority(revisedLocationPriority);
                    itineraryLocationRepository.save(itineraryLocation);
                }else {
                    return;
                }
                break;
            }
        }
        for (ItineraryLocation itineraryLocation : allItineraryLocations) {
            if (itineraryLocation.getItineraryId().equals(itineraryId) &&
                itineraryLocation.getPriority().equals(revisedLocationPriority) &&
                !itineraryLocation.getLocationId().equals(locationId)) {
                itineraryLocation.setPriority(currentLocationPriority);
                itineraryLocationRepository.save(itineraryLocation);
                break;
            }
        }
    }

    @PostMapping("/decreaseLocationPriority")
    public void decreaseLocationPriority(@RequestParam Long itineraryId, @RequestParam Long locationId) {
        //System.out.println("Changing Priority For: " + locationId + " On Itinerary: " + itineraryId);
        List<ItineraryLocation> allItineraryLocations = itineraryLocationRepository.findAll();
        Long currentLocationPriority = 0L;
        Long revisedLocationPriority = 0L;
        for (ItineraryLocation itineraryLocation : allItineraryLocations) {
            if (itineraryLocation.getItineraryId().equals(itineraryId) &&
                itineraryLocation.getLocationId().equals(locationId)) {
                currentLocationPriority = itineraryLocation.getPriority();
                revisedLocationPriority = currentLocationPriority + 1;
                itineraryLocation.setPriority(revisedLocationPriority);
                itineraryLocationRepository.save(itineraryLocation);
                break;
            }
        }
        for (ItineraryLocation itineraryLocation : allItineraryLocations) {
            if (itineraryLocation.getItineraryId().equals(itineraryId) &&
                !itineraryLocation.getLocationId().equals(locationId) &&
                itineraryLocation.getPriority().equals(revisedLocationPriority)) {
                itineraryLocation.setPriority(currentLocationPriority);
                itineraryLocationRepository.save(itineraryLocation);
                break;
            }
        }
    }

    @GetMapping("/getLocationPriority/{itineraryId}/{locationId}")
    public Long getLocationPriority(@PathVariable Long itineraryId, @PathVariable Long locationId) {
        List<ItineraryLocation> allItineraryLocations = itineraryLocationRepository.findAll();
        for (ItineraryLocation itineraryLocation : allItineraryLocations) {
            if (itineraryLocation.getItineraryId().equals(itineraryId) &&
                itineraryLocation.getLocationId().equals(locationId)) {
                return itineraryLocation.getPriority();
            }
        }
        return 0L;
    }

    @PostMapping("/toggleLocationPaid")
    public void toggleLocationPaid(@RequestParam Long locationId) {
        Location selectedLocation = locationRepository.getReferenceById(locationId);
        selectedLocation.setPaid(!selectedLocation.isPaid());
        locationRepository.save(selectedLocation);
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
