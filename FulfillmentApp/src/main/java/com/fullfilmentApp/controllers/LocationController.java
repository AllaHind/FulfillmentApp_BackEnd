package com.fullfilmentApp.controllers;

import com.fullfilmentApp.models.Location;
import com.fullfilmentApp.repository.LocationRepository;
import com.fullfilmentApp.services.LocationService;
import com.fullfilmentApp.services.LocationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private LocationServiceImp locationServiceImp;

    @GetMapping("/{code}")
    public Location findLocation(@PathVariable("code") String code) {
        return locationService.getLocation(code);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveLocation(@RequestBody Location Location) {
        return locationService.saveLocation(Location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocation(@RequestBody Location Location, @PathVariable("id") Long id) {
        return locationService.updateLocation(Location, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable("id") Long id) {
        return locationService.deleteLocation(id);
    }

    @GetMapping("")
    public List<Location> listLocation() {
        return locationService.listLocation();
    }
     @GetMapping("/notTaken")
    public List<Location> findAllByIsTakenIsFalse() {
        return locationServiceImp.findAllByTakenIsFalse();
    }
}
