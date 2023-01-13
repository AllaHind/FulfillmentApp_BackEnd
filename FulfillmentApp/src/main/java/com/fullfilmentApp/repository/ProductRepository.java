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
    @Query("select count(*) from Product  p where p.status LIKE 'OUT_OF_STOCK'")
    int out_of_stock();
    @Query(nativeQuery = true,value = "SELECT p.name FROM order_item,product p where p.id=order_item.product_id GROUP BY order_item.product_id ORDER BY Count(*) DESC LIMIT 1")
    String mostOrderedProduct();
}
