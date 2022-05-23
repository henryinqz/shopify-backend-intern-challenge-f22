package com.henryinqz.shopifybackendinternchallengef22.controller;

import com.henryinqz.shopifybackendinternchallengef22.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<?> listAll() {
        return inventoryService.listAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> list(@PathVariable String id) {
        return inventoryService.list(id);
    }
    @PostMapping
    public ResponseEntity<?> listByLocationId(@RequestBody Map<String, Object> payload) {
        return inventoryService.listByLocationId(payload);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        return inventoryService.create(payload);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestBody Map<String, Object> payload) {
        return inventoryService.update(id, payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return inventoryService.delete(id);
    }
}
