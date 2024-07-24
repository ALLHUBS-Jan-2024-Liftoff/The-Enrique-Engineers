package com.launchtrip.launchtrip.models.data;

import com.launchtrip.launchtrip.models.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
}
