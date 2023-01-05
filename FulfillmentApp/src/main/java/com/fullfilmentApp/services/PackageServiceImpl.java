package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Package;
import com.fullfilmentApp.models.Product;
import com.fullfilmentApp.payload.response.MessageResponse;
import com.fullfilmentApp.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepository packageRepository;
    @Override
    public Package findPackage(String code) {
        return packageRepository.findByCode(code);
    }

    @Override
    public ResponseEntity<?> savePackage(Package packagee) {
        Random random = new Random();
        int randomNumber = random.nextInt(99999 - 10000 + 1) + 10000;
        packagee.setCode("PKG-"+randomNumber);
        packageRepository.save(packagee);
        return  ResponseEntity
                .ok()
                .body(new MessageResponse("Package has been registered successfully!"));
    }

    @Override
    public ResponseEntity<?> updatePackage(Package packagee, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletePackage(Long id) {
        return null;
    }

    @Override
    public List<Package> listPackage() {
        return packageRepository.findAll();
    }



}
