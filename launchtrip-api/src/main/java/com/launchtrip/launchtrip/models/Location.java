package com.launchtrip.launchtrip.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String usState;
    private String country;
    private String postcode;
    private List<String> categories;
    private boolean visited;

    public Location() {
    }

    public Location(String name, String usState, String country, String postcode, List<String> categories) {
        this.name = name;
        this.usState = usState;
        this.country = country;
        this.postcode = postcode;
        this.categories = categories;
        this.visited = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsState() {
        return usState;
    }

    public void setUsState(String usState) {
        this.usState = usState;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}



