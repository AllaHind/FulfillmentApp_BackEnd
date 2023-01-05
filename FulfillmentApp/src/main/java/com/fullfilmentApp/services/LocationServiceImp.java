package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Location;
import com.fullfilmentApp.payload.response.MessageResponse;
import com.fullfilmentApp.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImp implements  LocationService{

    @Autowired
    private LocationRepository locationRepository;
    @Override
    public ResponseEntity<?> saveLocation(Location location) {
        location.setCode(location.getAils()+"-"+location.getRack()+"-"+location.getShelf()+"-"+location.getBin());
        if(getLocation(location.getCode())!=null)
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Location already exists!"));
        }
        location.setTaken(false);
        locationRepository.save(location);
        return ResponseEntity
                .ok()
                .body(new MessageResponse("Location has been registered successfully!"));
    }

    @Override
    public List<Location> listLocation() {
        return locationRepository.findAll();
    }

    @Override
    public ResponseEntity<?> deleteLocation(Long id) {
        locationRepository.deleteById(id);
        return ResponseEntity
                .ok()
                .body(new MessageResponse("Location has been deleted successfully!"));
    }

    @Override
    public Location getLocation(String code) {
        return locationRepository.findByCode(code);
    }

    @Override
    public ResponseEntity<?> updateLocation(Location location, long id) {
        location.setId(id);
        locationRepository.save(location);
        return ResponseEntity
                .ok()
                .body(new MessageResponse("Location has been updated successfully!"));

    }
}
