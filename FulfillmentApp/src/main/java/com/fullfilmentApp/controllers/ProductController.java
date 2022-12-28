package com.fullfilmentApp.controllers;

import com.fullfilmentApp.models.Product;
import com.fullfilmentApp.services.ProductService;
import com.fullfilmentApp.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductServiceImpl productServiceimp;

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable("id") String sku) {
        return productService.findProduct(sku);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {

        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("id") Long id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("")
    public List<Product> listProduct() {
        return productService.listProduct();
    }

    @Query("select p from Product p where p.name LIKE :x% or p.sku LIKE :x%")
    @GetMapping("/findByAttributes/{x}")
    public List<Product> findProductsByAttributes(@PathVariable("x") String x) {
        return productService.findProductsByAttributes(x);
    }

    @GetMapping("/outOfStock")
    public int out_of_stock() {
        return productServiceimp.out_of_stock();
    }
}
