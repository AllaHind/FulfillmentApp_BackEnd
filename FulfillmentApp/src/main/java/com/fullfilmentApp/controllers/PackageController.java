package com.fullfilmentApp.controllers;

import com.fullfilmentApp.models.Category;
import com.fullfilmentApp.models.Order;
import com.fullfilmentApp.models.Package;
import com.fullfilmentApp.repository.CategoryRepository;
import com.fullfilmentApp.repository.PackageRepository;
import com.fullfilmentApp.services.CategoryService;
import com.fullfilmentApp.services.PackageService;
import com.fullfilmentApp.services.PackageServiceImpl;
import com.fullfilmentApp.services.PackagingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/packages")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @Autowired
    private PackagingServiceImpl packagingService;

    @GetMapping("/{code}")
    public Package findPackage(@PathVariable("code") String code) {
        return packageService.findPackage(code);
    }

    @PostMapping("/")
    public ResponseEntity<?> savePackage(@RequestBody Package packagee) {
        return packageService.savePackage(packagee);
    }


    @GetMapping("")
    public List<Package> listPackage() {
        return packageService.listPackage();
    }


}
