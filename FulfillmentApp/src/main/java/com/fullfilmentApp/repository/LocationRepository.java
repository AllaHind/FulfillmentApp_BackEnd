package com.fullfilmentApp.repository;

import com.fullfilmentApp.models.Category;
import com.fullfilmentApp.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
    Location findByCode(String code);
}
