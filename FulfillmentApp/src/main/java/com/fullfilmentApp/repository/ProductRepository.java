package com.fullfilmentApp.repository;

import com.fullfilmentApp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findBySku(String sku);
    @Query("select p from Product p where p.name LIKE :x% or p.sku LIKE :x%")
    List<Product> findProductsByAttributes(@Param("x") String x) ;

}
