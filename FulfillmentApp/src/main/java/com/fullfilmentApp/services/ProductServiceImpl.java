package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Product;
import com.fullfilmentApp.payload.response.MessageResponse;
import com.fullfilmentApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findProduct(String sku) {
        return productRepository.findBySku(sku);
    }

    public ResponseEntity<?> saveProduct(Product product) {
        if (findProduct(product.getSku()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Product already exists!"));
        } else productRepository.save(product);
        return ResponseEntity
                .ok()
                .body(new MessageResponse("Product has been registered successfully!"));
    }

    @Override
    public ResponseEntity<?> updateProduct(Product product,Long id) {
        product.setId(id);
        productRepository.save(product);
        return ResponseEntity.ok(new MessageResponse("Product has been registered successfully!"));


    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Product has been deleted successfully!"));

    }

    @Override
    public List<Product> listProduct() {
        return productRepository.findAll();
    }
}
