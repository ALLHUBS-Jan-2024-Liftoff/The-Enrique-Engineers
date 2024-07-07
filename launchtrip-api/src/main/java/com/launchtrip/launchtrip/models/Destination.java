package com.launchtrip.launchtrip.models;

import jakarta.persistence.Entity;

@Entity
public class Destination {
    private String name;

    private String address;

    public Destination(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
