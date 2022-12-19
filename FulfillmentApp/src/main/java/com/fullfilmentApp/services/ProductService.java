package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public Product findProduct(String sku);
    public ResponseEntity<?> saveProduct(Product product);
    public ResponseEntity<?> updateProduct(Product product,Long id);
    public ResponseEntity<?> deleteProduct(Long id);

    public List<Product> listProduct();
    @Query("select p from Product p where p.name LIKE :x% or p.sku LIKE :x%")
    List<Product> findProductsByAttributes(@Param("x") String x) ;
}
