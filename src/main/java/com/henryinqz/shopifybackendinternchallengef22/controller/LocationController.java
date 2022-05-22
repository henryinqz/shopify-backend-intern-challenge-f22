package com.henryinqz.shopifybackendinternchallengef22.controller;

import com.henryinqz.shopifybackendinternchallengef22.model.Location;
import com.henryinqz.shopifybackendinternchallengef22.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return locationService.list();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Location newLocation) {
        return locationService.create(newLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestBody Location updatedLocation) {
        return locationService.update(id, updatedLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return locationService.delete(id);
    }
}
