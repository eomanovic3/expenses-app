package com.expenses.demo.services;

import com.expenses.demo.models.Expense;
import com.expenses.demo.models.Location;
import com.expenses.demo.repositories.ExpensesRepository;
import com.expenses.demo.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public ResponseEntity getLocations() {

        List<Location> locationsFromDatabase = locationRepository.findAll();

        if (locationsFromDatabase == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<List>(locationsFromDatabase, HttpStatus.OK);
    }

    public ResponseEntity<?> getLocation(Integer id) {

        Location locationFromDatabase = locationRepository.findByLocationId(id);

        if (locationFromDatabase == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity(locationFromDatabase, HttpStatus.OK);
    }

    public ResponseEntity deleteLocation(Integer id) {
        locationRepository.deleteById(id);
        List<Location> locations = locationRepository.findAll();
        return new ResponseEntity(locations, HttpStatus.OK);
    }

    public ResponseEntity addLocation(Location location) {
        locationRepository.save(location);
        return new ResponseEntity(location, HttpStatus.OK);
    }
}
