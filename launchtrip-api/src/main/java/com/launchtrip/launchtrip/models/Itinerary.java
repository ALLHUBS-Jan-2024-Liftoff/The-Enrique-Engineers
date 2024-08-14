package com.launchtrip.launchtrip.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean visited;

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany
    private List<Location> locations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Itinerary() {
    }

    public Itinerary(String description, Boolean visited) {
        this.name = description;
        this.visited = visited;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public void removeLocation(Location location) {
        locations.remove(location);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview (Review review) {
        if (this.visited) {
            this.reviews.add(review);
            review.setItinerary(this);
        } else {
            throw new IllegalStateException("Cannot add review unless the itinerary has been visited");
        }
    }
}