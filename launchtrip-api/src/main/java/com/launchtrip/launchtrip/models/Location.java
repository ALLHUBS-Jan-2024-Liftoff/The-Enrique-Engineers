package com.launchtrip.launchtrip.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    //private String placeId;
    private String usState;
    private String country;
    private String postcode;
    private List<String> categories;
    private boolean visited;
    private boolean paid;

    private Long itineraryId;

    public Location() {
    }

    public Location(String name, String city, String usState, String country, String postcode, List<String> categories) {
        this.name = name;
        this.city = city;
        //this.placeId = placeId;
        this.usState = usState;
        this.country = country;
        this.postcode = postcode;
        this.categories = categories;
        this.visited = false;
        this.paid = true; //Default value of true unless GeoAPIFy shows that it's free
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

    public java.lang.String getCity() {
        return city;
    }

    public void setCity(java.lang.String city) {
        this.city = city;
    }

    /*public java.lang.String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(java.lang.String placeId) {
        this.placeId = placeId;
    }*/

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

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Long getItineraryId() {
        return itineraryId;
    }
}



