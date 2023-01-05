package com.fullfilmentApp.repository;

import com.fullfilmentApp.models.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.List;

public interface PackageRepository extends JpaRepository<Package,Long> {
    Package findByCode(String code);
    @Query(nativeQuery = true,value = "select * from Package p where  p.max_weight>:weight and p.height*p.length*p.width>:volume and p.status<>'Not Available' LIMIT 1" )
    Package packagee(@Param("weight") double weight, @Param("volume") double volume);

}
