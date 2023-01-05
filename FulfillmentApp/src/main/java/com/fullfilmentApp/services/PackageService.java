package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Package;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PackageService {
    
    Package findPackage(String label);
    public ResponseEntity<?> savePackage(Package packagee);
    public ResponseEntity<?> updatePackage(Package packagee,Long id);
    public ResponseEntity<?> deletePackage(Long id);

    public List<Package> listPackage();
    
    
}
