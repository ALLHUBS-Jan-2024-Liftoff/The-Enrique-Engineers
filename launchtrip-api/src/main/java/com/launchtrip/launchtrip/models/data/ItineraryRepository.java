package com.launchtrip.launchtrip.models.data;

import com.launchtrip.launchtrip.models.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    static List<Itinerary> findByVisited(Boolean visited) {
        return null;
    }
}
