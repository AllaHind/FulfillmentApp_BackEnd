package com.fullfilmentApp.repository;

import com.fullfilmentApp.models.Package;
import com.fullfilmentApp.models.Packaging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PackagingRepository extends JpaRepository<Packaging,Long> {
    Packaging findByCode(String code);
    @Query(nativeQuery = true,value = "select count(p.id) from package p where p.packaging_id= :x")
    int numberOfPackages(@Param("x") int id);

    Packaging findByOrderCode(String orderCode);

}
