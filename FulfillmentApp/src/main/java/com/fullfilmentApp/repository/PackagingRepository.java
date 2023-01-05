package com.fullfilmentApp.repository;

import com.fullfilmentApp.models.Package;
import com.fullfilmentApp.models.Packaging;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackagingRepository extends JpaRepository<Packaging,Long> {
    Packaging findByCode(String code);
}
