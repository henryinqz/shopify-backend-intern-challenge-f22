package com.henryinqz.shopifybackendinternchallengef22.controller;

import com.henryinqz.shopifybackendinternchallengef22.model.Inventory;
import com.henryinqz.shopifybackendinternchallengef22.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return inventoryService.list();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Inventory newEntry) {
        return inventoryService.create(newEntry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestBody Inventory updatedEntry) {
        return inventoryService.update(id, updatedEntry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return inventoryService.delete(id);
    }
}
