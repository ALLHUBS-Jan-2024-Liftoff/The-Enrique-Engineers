package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.models.Destination;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LaunchTripController {

    @PostMapping
    public void AddDestination(Destination destination) {

    }

    @PostMapping
    public void RemoveDestination(Destination destination) {

    }
}
