package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Location;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationService {

    public ResponseEntity<?> saveLocation(Location location);
    public List<Location> listLocation();
    public ResponseEntity<?> deleteLocation(Long id);
    public Location getLocation(String code);
    ResponseEntity<?> updateLocation(Location location,long id);


}
