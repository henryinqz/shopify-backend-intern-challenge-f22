package com.henryinqz.shopifybackendinternchallengef22.service;

import com.henryinqz.shopifybackendinternchallengef22.model.Inventory;
import com.henryinqz.shopifybackendinternchallengef22.payload.response.APIResponse;
import com.henryinqz.shopifybackendinternchallengef22.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;

    public ResponseEntity<?> list() {
        return ResponseEntity.ok(inventoryRepository.findAll());
    }

    public ResponseEntity<?> create(Inventory inventoryEntry) {
        inventoryRepository.save(inventoryEntry);
        return ResponseEntity.ok(new APIResponse(true, "Created inventory entry " + inventoryEntry.id));
    }

    public ResponseEntity<?> update(String id, Inventory updatedEntry) {
        Inventory inventoryEntry = inventoryRepository.findById(id).orElse(null);
        if (inventoryEntry == null)
            return ResponseEntity.notFound().build();

        inventoryEntry.name = updatedEntry.name;
        inventoryEntry.quantity = updatedEntry.quantity;
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
