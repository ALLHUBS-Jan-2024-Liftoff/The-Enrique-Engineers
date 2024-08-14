package com.launchtrip.launchtrip.services;

import com.launchtrip.launchtrip.models.Itinerary;
import com.launchtrip.launchtrip.models.Review;
import com.launchtrip.launchtrip.models.data.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    public void addReviewToItinerary(Long itineraryId, Review review) {
        Itinerary itinerary = itineraryRepository.findById(itineraryId).orElseThrow(() -> new IllegalArgumentException("Invalid itinerary ID"));

        // check if itinerary has been visited
        if (itinerary.getVisited()) {
            itinerary.addReview(review);
            itineraryRepository.save(itinerary);
        } else {
            throw new IllegalStateException("Cannot add review unless itinerary has been visited.");
        }
    }

}
