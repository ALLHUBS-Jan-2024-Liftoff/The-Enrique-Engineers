package com.launchtrip.launchtrip.services;

import com.launchtrip.launchtrip.models.Location;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationOfTheDayService {

    private final SearchService searchService;
    private List<Location> cachedLocations;  // Cache for locations

    public LocationOfTheDayService(SearchService searchService) {
        this.searchService = searchService;
        this.cachedLocations = new ArrayList<>();
    }

    public String getRandomLocationName() throws IOException {
        // Check if the cached locations list is empty
        if (cachedLocations.isEmpty()) {
            System.out.println("Fetching locations from GeoApify API...");
            cachedLocations = searchService.searchLocationsFromQuery("all");

            // If no locations are found, throw an exception
            if (cachedLocations.isEmpty()) {
                throw new IOException("No valid locations found from GeoApify API");
            }
        }

        // Initialize index and select a random location
        int index = 0;
        for (int i = 1; i < cachedLocations.size(); i++) {
            if (!cachedLocations.get(i).getName().isEmpty()) {
                index = i;
                break;
            }
        }

        // Return the name of the selected location
        return cachedLocations.get(index).getName();
    }
}
