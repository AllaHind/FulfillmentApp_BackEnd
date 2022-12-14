package com.fullfilmentApp.repository;

import com.fullfilmentApp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findBySku(String sku);
}
