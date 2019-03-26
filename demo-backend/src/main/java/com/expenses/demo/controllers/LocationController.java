package com.expenses.demo.controllers;

import com.expenses.demo.models.Location;
import com.expenses.demo.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping()
    public ResponseEntity getLocations() {
        return locationService.getLocations();
    }

    @GetMapping(value = "/getLocation/{id}")
    public ResponseEntity getLocation(@PathVariable Integer id) {
        return locationService.getLocation(id);
    }

    @DeleteMapping(value = "/deleteLocation/{id}")
    public ResponseEntity deleteLocation(@PathVariable Integer id) {
        return locationService.deleteLocation(id);
    }

    @PostMapping(value = "/addLocation")
    public ResponseEntity addExpense(@RequestBody Location location){
        return  locationService.addLocation(location);
    }
}
