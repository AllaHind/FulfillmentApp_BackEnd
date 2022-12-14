package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public Product findProduct(String sku);
    public ResponseEntity<?> saveProduct(Product product);
    public ResponseEntity<?> updateProduct(Product product,Long id);
    public ResponseEntity<?> deleteProduct(Long id);

    public List<Product> listProduct();

}
