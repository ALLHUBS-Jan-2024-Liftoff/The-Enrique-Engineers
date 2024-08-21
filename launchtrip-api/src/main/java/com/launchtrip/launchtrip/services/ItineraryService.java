package com.launchtrip.launchtrip.services;

import com.launchtrip.launchtrip.models.Itinerary;
import com.launchtrip.launchtrip.models.data.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    public Itinerary getItineraryViaId(Long itineraryId) {
        return itineraryRepository.getReferenceById(itineraryId);
    }

    public void toggleItineraryVisited(Itinerary itinerary) {
        if (itinerary.isVisited()) {
            itinerary.setVisited(false);
        }
        else {
            itinerary.setVisited(true);
        }
        itineraryRepository.save(itinerary);
    }

}
