package com.launchtrip.launchtrip.services;

import com.launchtrip.launchtrip.models.Location;
import org.springframework.stereotype.Service;

import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;

@Service
public class LocationOfTheDayService {

    private final SearchService searchService;
    private List<Location> cachedLocations;  // Cache for locations
    private int currentIndex = -1;  // Keeps track of the current index

    public LocationOfTheDayService(SearchService searchService) {
        this.searchService = searchService;
        this.cachedLocations = null;
    }

    public String getRandomLocationName() throws IOException {
        // Check if the cached locations list is empty
        if (cachedLocations == null || cachedLocations.isEmpty()) {
            System.out.println("Fetching locations from GeoApify API...");
            cachedLocations = searchService.searchLocationsFromQuery("all");

            // If no locations are found, throw an exception
            if (cachedLocations.isEmpty()) {
                throw new IOException("No valid locations found from GeoApify API");
            }
        }

        // Increment the currentIndex to select the next location
        currentIndex = (currentIndex + 1) % cachedLocations.size();

        // Return the name of the selected location
        return cachedLocations.get(currentIndex).getName();
    }
}
