package com.abaxconsulting.AbaxCRM.service;

import com.abaxconsulting.AbaxCRM.model.Location;
import com.abaxconsulting.AbaxCRM.repository.LocationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location save(Location location) {
        Location newLocation =  locationRepository.save(location);
        return newLocation;
    }

    public Optional<Location> getLocation(UUID locationID) {
        Optional<Location> existingLocation = locationRepository.findById(locationID);
        return existingLocation;

    }

    public Iterable<Location> getAllLocations() {
        Iterable<Location> locationsList = locationRepository.findAll();
        return locationsList;
    }

    public void delete(UUID id) {
        locationRepository.deleteById(id);
    }

    public int update(UUID locationID, Location location) {
        int result = locationRepository.updateAddressAndCityAndCountryAndNameAndStateAndTaxRateAndZipById(
                location.getAddress(), location.getCity(), location.getCountry(), location.getName(),
                location.getState(), location.getTaxRate(), location.getZip(), locationID);

        return result;
    }
}
