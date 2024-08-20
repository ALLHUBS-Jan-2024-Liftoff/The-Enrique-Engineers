package com.launchtrip.launchtrip.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ItineraryLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itineraryId;

    private Long locationId;

    private Boolean visitedLocation;

    public ItineraryLocation() {}

    public ItineraryLocation(Long itineraryId, Long locationId) {
        this.itineraryId = itineraryId;
        this.locationId = locationId;
        this.visitedLocation = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Boolean getVisitedLocation() {
        return visitedLocation;
    }

    public void setVisitedLocation(Boolean visitedLocation) {
        this.visitedLocation = visitedLocation;
    }
}
