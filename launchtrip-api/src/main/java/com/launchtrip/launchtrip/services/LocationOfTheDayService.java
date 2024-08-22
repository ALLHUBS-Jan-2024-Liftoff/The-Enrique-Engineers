package com.launchtrip.launchtrip.services;

import com.launchtrip.launchtrip.models.Location;
import com.launchtrip.launchtrip.models.data.LocationRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LocationOfTheDayService {

    private final LocationRepository locationRepository;

    public LocationOfTheDayService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public String getRandomLocationName() throws IOException {

        Long randomId = (long) ((Math.random() * 50) + 1);


        Location location = locationRepository.findById(randomId).orElse(null);

        if (location != null) {
            return location.getName();
        } else {

            throw new IOException("No location found with ID: " + randomId);
        }
    }
}
