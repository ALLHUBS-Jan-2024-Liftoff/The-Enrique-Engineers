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

    @OneToMany
    private List<Location> locations = new ArrayList<>();

    public Itinerary() {
    }

    public Itinerary(String description, String assigned) {
        this.name = description;
        this.visited = false;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location newLocation) {
        locations.add(newLocation);
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

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }
}