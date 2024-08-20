package com.launchtrip.launchtrip.models.data;

import com.launchtrip.launchtrip.models.ItineraryLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryLocationRepository extends JpaRepository<ItineraryLocation, Long> {
}
