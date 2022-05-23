package com.henryinqz.shopifybackendinternchallengef22.service;

import com.henryinqz.shopifybackendinternchallengef22.model.Inventory;
import com.henryinqz.shopifybackendinternchallengef22.model.Location;
import com.henryinqz.shopifybackendinternchallengef22.payload.response.APIResponse;
import com.henryinqz.shopifybackendinternchallengef22.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    InventoryService inventoryService;

    public ResponseEntity<?> list() {
        return ResponseEntity.ok(locationRepository.findAll());
    }

    public ResponseEntity<?> listLocation(String id) {
        Location location = locationRepository.findById(id).orElse(null);
        if (location == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(location);
    }

    public ResponseEntity<?> create(Map<String, Object> payload) {
        // Extract values from JSON object
        if (payload == null) return ResponseEntity.unprocessableEntity().build();

        String name = (String) payload.get("name");
        String address = (String) payload.get("address");
        String zipCode = (String) payload.get("zipCode");
        String city = (String) payload.get("city");
        String state = (String) payload.get("state");
        String country = (String) payload.get("country");

        if (!payload.containsKey("name") || name == null || name.equals(""))
            return ResponseEntity.badRequest().body(new APIResponse(false, "Body is missing 'name' field"));
        else if (!payload.containsKey("address") || address == null || address.equals(""))
            return ResponseEntity.badRequest().body(new APIResponse(false, "Body is missing 'address' field"));
        else if (!payload.containsKey("zipCode") || zipCode == null || zipCode.equals(""))
            return ResponseEntity.badRequest().body(new APIResponse(false, "Body is missing 'zipCode' field"));
        else if (!payload.containsKey("city") || city == null || city.equals(""))
            return ResponseEntity.badRequest().body(new APIResponse(false, "Body is missing 'city' field"));
        else if (!payload.containsKey("state") || state == null || state.equals(""))
            return ResponseEntity.badRequest().body(new APIResponse(false, "Body is missing 'state' field"));
        else if (!payload.containsKey("country") || country == null || country.equals(""))
            return ResponseEntity.badRequest().body(new APIResponse(false, "Body is missing 'country' field"));

        // Create location
        Location location = new Location(name, address, zipCode, city, state, country);
        locationRepository.save(location);
        return ResponseEntity.ok(new APIResponse(true, "Created location " + location.id));
    }

    public ResponseEntity<?> update(String id, Map<String, Object> payload) {
        Location location = locationRepository.findById(id).orElse(null);
        if (location == null) return ResponseEntity.notFound().build();

        // Extract values from JSON object
        if (payload == null) return ResponseEntity.unprocessableEntity().build();

        String updatedName = (String) payload.get("name");
        String updatedAddress = (String) payload.get("address");
        String updatedZipCode = (String) payload.get("zipCode");
        String updatedCity = (String) payload.get("city");
        String updatedState = (String) payload.get("state");
        String updatedCountry = (String) payload.get("country");

        // Update location
        if (payload.containsKey("name") && updatedName != null || !updatedName.equals(""))            location.name = updatedName;
        if (payload.containsKey("address") && updatedAddress != null && !updatedAddress.equals(""))   location.address = updatedAddress;
        if (payload.containsKey("zipCode") && updatedZipCode != null && !updatedZipCode.equals(""))   location.zipCode = updatedZipCode;
        if (payload.containsKey("city") && updatedCity != null && !updatedCity.equals(""))            location.city = updatedCity;
        if (payload.containsKey("state") && updatedState != null && !updatedState.equals(""))         location.state = updatedState;
        if (payload.containsKey("country") && updatedCountry != null && !updatedCountry.equals(""))   location.country= updatedCountry;
        locationRepository.save(location);

        // Update inventory entries associated w/ location
        ResponseEntity<?> inventoryResponse = inventoryService.listByLocationId(location.id);
        if (inventoryResponse.getStatusCode() == HttpStatus.OK) {
            List<Inventory> inventoryListToUpdate = (List<Inventory>) inventoryResponse.getBody();
            Map<String, Object> locationIdMap = new HashMap<>();
            locationIdMap.put("locationId", location.id);
            inventoryListToUpdate.forEach(inventoryEntryToUpdate -> inventoryService.update(inventoryEntryToUpdate.id, locationIdMap));
        }

        return ResponseEntity.ok(new APIResponse(true, "Updated location " + id));
    }

    public ResponseEntity<?> delete(String id) {
       Location location = locationRepository.findById(id).orElse(null);
       if (location == null)
           return ResponseEntity.notFound().build();

        // Delete inventory entries associated w/ location
        ResponseEntity<?> inventoryResponse = inventoryService.listByLocationId(location.id);
        if (inventoryResponse.getStatusCode() == HttpStatus.OK) {
            List<Inventory> inventoryListToDelete = (List<Inventory>) inventoryResponse.getBody();
            Map<String, Object> locationIdMap = new HashMap<>();
            locationIdMap.put("locationId", location.id);
            inventoryListToDelete.forEach(inventoryEntryToDelete -> inventoryService.delete(inventoryEntryToDelete.id));
        }

       locationRepository.delete(location);
       return ResponseEntity.ok(new APIResponse(true, "Deleted location " + id));
    }
}
