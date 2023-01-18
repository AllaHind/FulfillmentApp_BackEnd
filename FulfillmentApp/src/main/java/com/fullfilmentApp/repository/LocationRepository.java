package com.fullfilmentApp.repository;

import com.fullfilmentApp.models.Category;
import com.fullfilmentApp.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Long> {
    Location findByCode(String code);
    @Query(nativeQuery = true,value = "select * from location l where l.is_taken=false LIMIT 1")
    Location findEmptyLocation();
    List<Location> findAllByIsTakenIsFalse();

    @Query(nativeQuery = true,value = "select count(*) from location l where l.is_taken=false")
    int countNotTaken();


}
