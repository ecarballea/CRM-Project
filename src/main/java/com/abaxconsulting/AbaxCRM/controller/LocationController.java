package com.abaxconsulting.AbaxCRM.controller;

import com.abaxconsulting.AbaxCRM.exception.LocationNotFoundException;
import com.abaxconsulting.AbaxCRM.model.Location;
import com.abaxconsulting.AbaxCRM.service.LocationService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("")
    public ResponseEntity<?> newLocation(@RequestBody @Valid @Nonnull Location location){
        if (location == null) {
            throw new IllegalArgumentException("Location is missing");
        } else{
            Location newLocation = locationService.save(location);
            return new ResponseEntity<Location>(newLocation, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{locationID}")
    public ResponseEntity<?> getLocation(@Valid @Nonnull @PathVariable UUID locationID){
        Optional<Location> existingLocation = locationService.getLocation(locationID);
        if (existingLocation.isEmpty()){
            throw new LocationNotFoundException("The Location ID '"+locationID+"' does not exist");
        } else {
            return new ResponseEntity<Location>(existingLocation.get(), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllLocations(){
        Iterable<Location> locationsList = locationService.getAllLocations();
        if (((ArrayList) locationsList).size() == 0){
            throw new LocationNotFoundException("There are not any Location yet");
        } else {
            return new ResponseEntity<Iterable<Location>>(locationsList, HttpStatus.OK);
        }
    }

    @PatchMapping("/{locationID}")
    public ResponseEntity<?> updateLocation(@RequestBody @Valid @Nonnull Location location,
                                            @Valid @Nonnull @PathVariable UUID locationID){
        if (location == null) {
            throw new IllegalArgumentException("Location is missing");
        } else if (locationID == null) {
            throw new IllegalArgumentException("Location ID is missing");
        } else {
            Optional<Location> existingLocation = locationService.getLocation(locationID);
            if (existingLocation.isEmpty()){
                throw new LocationNotFoundException("The Location ID '"+locationID+"' does not exist");
            } else {
                int response = locationService.update(locationID, location);
                return new ResponseEntity<Location>(HttpStatus.OK);
            }
        }
    }

    @DeleteMapping("/{locationID}")
    public ResponseEntity<?> deleteLocation(@Valid @Nonnull @PathVariable UUID locationID){
        if (locationID == null) {
            throw new IllegalArgumentException("Location ID is missing");
        } else {
            Optional<Location> existingLocation = locationService.getLocation(locationID);
            if (existingLocation.isEmpty()){
                throw new LocationNotFoundException("The Location ID '"+locationID+"' does not exist");
            } else {
                locationService.delete(existingLocation.get().getId());
                return new ResponseEntity<Location>(HttpStatus.OK);
            }
        }
    }

}
