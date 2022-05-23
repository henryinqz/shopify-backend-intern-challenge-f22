package com.henryinqz.shopifybackendinternchallengef22.service;

import com.henryinqz.shopifybackendinternchallengef22.model.Inventory;
import com.henryinqz.shopifybackendinternchallengef22.model.Location;
import com.henryinqz.shopifybackendinternchallengef22.payload.response.APIResponse;
import com.henryinqz.shopifybackendinternchallengef22.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    @Lazy
    LocationService locationService;

    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(inventoryRepository.findAll());
    }

    public ResponseEntity<?> list(String id) {
        Inventory inventoryEntry = inventoryRepository.findById(id).orElse(null);
        if (inventoryEntry == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(inventoryEntry);
    }

    public ResponseEntity<?> listByLocationId(Map<String, Object> payload) {
        // Extract values from JSON object
        if (payload == null) return ResponseEntity.unprocessableEntity().build();

        String locationId = (String) payload.get("locationId");
        if (!payload.containsKey("locationId") || locationId == null || locationId.equals(""))
            return ResponseEntity.badRequest().body(new APIResponse(false, "Body is missing 'locationId' field"));

        return listByLocationId(locationId);
    }
    public ResponseEntity<?> listByLocationId(String locationId) {
        ResponseEntity<?> locationResponse = locationService.listLocation(locationId);
        if (locationResponse.getStatusCode() != HttpStatus.OK)
            return ResponseEntity.badRequest().body(new APIResponse(false, "Invalid 'locationId'"));

        return ResponseEntity.ok(inventoryRepository.findByLocationId(locationId));
    }

    public ResponseEntity<?> create(Map<String, Object> payload) {
        // Extract values from JSON object
        if (payload == null) return ResponseEntity.unprocessableEntity().build();

        String name = (String) payload.get("name");
        int quantity = (int) payload.get("quantity");
        String locationId = (String) payload.get("locationId");

        if (!payload.containsKey("name") || name == null || name.equals(""))
            return ResponseEntity.badRequest().body(new APIResponse(false, "Body is missing 'name' field"));
        else if (!payload.containsKey("quantity"))
            return ResponseEntity.badRequest().body(new APIResponse(false, "Body is missing 'quantity' field"));
        else if (!payload.containsKey("locationId") || locationId == null || locationId.equals(""))
            return ResponseEntity.badRequest().body(new APIResponse(false, "Body is missing 'locationId' field"));

        ResponseEntity<?> locationResponse = locationService.listLocation(locationId);
        if (locationResponse.getStatusCode() != HttpStatus.OK)
            return ResponseEntity.badRequest().body(new APIResponse(false, "Invalid 'locationId'"));

        // Create inventory entry
        Location location = (Location) locationResponse.getBody();
        Inventory inventoryEntry = new Inventory(name, quantity, location);
        inventoryRepository.save(inventoryEntry);
        return ResponseEntity.ok(new APIResponse(true, "Created inventory entry " + inventoryEntry.id));
    }

    public ResponseEntity<?> update(String id, Map<String, Object> payload) {
        Inventory inventoryEntry = inventoryRepository.findById(id).orElse(null);
        if (inventoryEntry == null) return ResponseEntity.notFound().build();

        // Extract values from JSON object
        if (payload == null) return ResponseEntity.unprocessableEntity().build();

        String updatedName = (String) payload.get("name");
        int updatedQuantity = payload.get("quantity") == null ? 0 : (int) payload.get("quantity");
        String updatedLocationId = (String) payload.get("locationId");

        Location location = null;
        if (payload.containsKey("locationId") && updatedLocationId != null && !updatedLocationId.equals("")) {
            ResponseEntity<?> locationResponse = locationService.listLocation(updatedLocationId);
            if (locationResponse.getStatusCode() != HttpStatus.OK)
                return ResponseEntity.badRequest().body(new APIResponse(false, "Invalid 'locationId'"));
            location = (Location) locationResponse.getBody();
        }

        // Update inventory entry
        if (payload.containsKey("name") && updatedName != null && !updatedName.equals("")) inventoryEntry.name = updatedName;
        if (payload.containsKey("quantity")) inventoryEntry.quantity = updatedQuantity;
        if (location != null) inventoryEntry.location = location;
        inventoryRepository.save(inventoryEntry);
        return ResponseEntity.ok(new APIResponse(true, "Updated inventory entry " + id));
    }

    public ResponseEntity<?> delete(String id) {
       Inventory inventoryEntry = inventoryRepository.findById(id).orElse(null);
       if (inventoryEntry == null)
           return ResponseEntity.notFound().build();

       inventoryRepository.delete(inventoryEntry);
       return ResponseEntity.ok(new APIResponse(true, "Deleted inventory entry " + id));
    }
}
