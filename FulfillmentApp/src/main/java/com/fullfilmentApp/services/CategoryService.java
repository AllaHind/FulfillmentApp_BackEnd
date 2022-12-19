package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Category;
import com.fullfilmentApp.models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    
    Category findCategory(String label);
    public ResponseEntity<?> saveCategory(Category Category);
    public ResponseEntity<?> updateCategory(Category Category,Long id);
    public ResponseEntity<?> deleteCategory(Long id);

    public List<Category> listCategory();
    
    
}
