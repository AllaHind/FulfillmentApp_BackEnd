package com.fullfilmentApp.services;

import com.fullfilmentApp.Enum.ProductStatus;
import com.fullfilmentApp.models.Category;
import com.fullfilmentApp.models.Location;
import com.fullfilmentApp.models.Product;
import com.fullfilmentApp.payload.response.MessageResponse;
import com.fullfilmentApp.repository.CategoryRepository;
import com.fullfilmentApp.repository.LocationRepository;
import com.fullfilmentApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

@Autowired
private LocationRepository locationRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public Product findProduct(String sku) {
        return productRepository.findBySku(sku);
    }

    public ResponseEntity<?> saveProduct(Product product) {

        if (findProduct(product.getSku()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Product already exists!"));
        }
        if (fieldNullOrEmpty(product.getSku()) || fieldNullOrEmpty(product.getName()) || fieldZero(product.getCostPrice()) || fieldZero(product.getSellingPrice())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("All fields are required!"));
        }
        if (product.getStockQuantity() < product.getSupplyLevel()) {
            product.setStatus(ProductStatus.OUT_OF_STOCK);
        } else {
            List<Location> locations=new ArrayList<>();
            Location location=findEmptyLocation();
            locations.add(location);
            product.setLocations(locations);
            location.setProduct(product);
            location.setTaken(true);
            product.setStatus(ProductStatus.IN_STOCK);
            Category category = categoryRepository.findByLabel(product.getCategory().getLabel());
            product.setCategory(category);
            productRepository.save(product);
            locationRepository.save(location);

        }
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("Product has been registered successfully!"));

    }

    @Override
    public ResponseEntity<?> updateProduct(Product product,Long id) {
        product.setId(id);
        if (fieldNullOrEmpty(product.getSku()) || fieldNullOrEmpty(product.getName()) || fieldZero(product.getCostPrice()) || fieldZero(product.getSellingPrice())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("All fields are required!"));
        }
        if (product.getStockQuantity() < product.getSupplyLevel()) {
            product.setStatus(ProductStatus.OUT_OF_STOCK);
        } else
            product.setStatus(ProductStatus.IN_STOCK);
        productRepository.save(product);
        return ResponseEntity.ok(new MessageResponse("Product has been updated successfully!"));


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

    @Override
    public List<Product> findProductsByAttributes(String x) {
        return productRepository.findProductsByAttributes(x);
    }


    public boolean fieldNullOrEmpty(String field)
    {
        if(field==null || field.isEmpty())
        {
            return true;

        }
        else return false;
    }
    public boolean fieldZero(double field)
    {
        if(field==0.0)
        {
            return true;

        }
        else return false;
    }

    @Query("select count(*) from Product  p where p.status LIKE 'OUT_OF_STOCK'")
    public int out_of_stock() {
        return productRepository.out_of_stock();
    }

    @Query(nativeQuery = true, value = "select * from location l where l.is_taken=false LIMIT 1")
    public Location findEmptyLocation() {
        return locationRepository.findEmptyLocation();
    }

    public String mostOrderedProduct() {
        return productRepository.mostOrderedProduct();
    }
}
