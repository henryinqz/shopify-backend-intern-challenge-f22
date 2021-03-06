package com.henryinqz.shopifybackendinternchallengef22.controller;

import com.henryinqz.shopifybackendinternchallengef22.model.Location;
import com.henryinqz.shopifybackendinternchallengef22.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping
    public ResponseEntity<?> list() {
        return locationService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listLocation(@PathVariable String id) {
        return locationService.listLocation(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        return locationService.create(payload);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestBody Map<String, Object> payload) {
        return locationService.update(id, payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return locationService.delete(id);
    }
}
