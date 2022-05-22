package com.henryinqz.shopifybackendinternchallengef22.service;

import com.henryinqz.shopifybackendinternchallengef22.model.Location;
import com.henryinqz.shopifybackendinternchallengef22.payload.response.APIResponse;
import com.henryinqz.shopifybackendinternchallengef22.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    public ResponseEntity<?> list() {
        return ResponseEntity.ok(locationRepository.findAll());
    }

    public ResponseEntity<?> create(Location location) {
        locationRepository.save(location);
        return ResponseEntity.ok(new APIResponse(true, "Created location " + location.id));
    }

    public ResponseEntity<?> update(String id, Location updatedLocation) {
        Location location = locationRepository.findById(id).orElse(null);
        if (location == null)
            return ResponseEntity.notFound().build();

        location.name = updatedLocation.name;
        location.address = updatedLocation.address;
        location.zipCode = updatedLocation.zipCode;
        location.city = updatedLocation.city;
        location.state = updatedLocation.state;
        location.country= updatedLocation.country;
        locationRepository.save(location);
        return ResponseEntity.ok(new APIResponse(true, "Updated location " + id));
    }

    public ResponseEntity<?> delete(String id) {
       Location location = locationRepository.findById(id).orElse(null);
       if (location == null)
           return ResponseEntity.notFound().build();

       locationRepository.delete(location);
       return ResponseEntity.ok(new APIResponse(true, "Deleted location " + id));
    }
}
