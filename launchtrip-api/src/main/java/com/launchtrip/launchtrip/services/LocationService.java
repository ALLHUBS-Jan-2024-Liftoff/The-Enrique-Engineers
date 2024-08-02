package com.launchtrip.launchtrip.services;

import com.launchtrip.launchtrip.models.data.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.launchtrip.launchtrip.models.Location;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public String downloadLocationsFromGeoapify(String searchQuery) {
        String cityName = "City Not Found";
        try {
            SearchService searchService = new SearchService();
            List<Location> downloadedLocations = searchService.searchLocationsFromQuery(searchQuery);
            for (Location location : downloadedLocations) {
                saveLocation(location);
                cityName = location.getCityName();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityName;
    }

    public List<Location> getAllStoredLocations() {
        return locationRepository.findAll();
    }

    public List<Location> getLocationsByCityName(String cityName) {
        return locationRepository.findByCityName(cityName);
    }

    public Location getLocationViaId(Long locationId) {
        return locationRepository.getReferenceById(locationId);
    }

    public void saveLocation(Location location) {
        if (!locationExists(location))
        {
            locationRepository.save(location);
        }
    }

    public boolean locationExists(Location location) {
        List<Location> allLocations = locationRepository.findAll();
        if (allLocations.isEmpty()) { return false;}
        for (Location storedLocation : allLocations) {
            String locationName = storedLocation.getName().toUpperCase();
            String locationState = storedLocation.getUsState().toUpperCase();
            String locationPostcode = storedLocation.getPostcode();
            if (location.getName().toUpperCase().equals(locationName) &&
                    location.getUsState().toUpperCase().equals(locationState) &&
                    location.getPostcode().equals(locationPostcode)) {
                return true;
            }
        }
        return false;
    }

    public void toggleLocationVisited(Location location) {
        if (location.isVisited()) {
            location.setVisited(false);
        }
        else {
            location.setVisited(true);
        }
        locationRepository.save(location);
    }
}
