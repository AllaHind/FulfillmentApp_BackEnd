package com.fullfilmentApp.controllers;

import com.fullfilmentApp.models.Category;
import com.fullfilmentApp.repository.CategoryRepository;
import com.fullfilmentApp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/{label}")
    public Category findCategory(@PathVariable("label") String label) {
        return categoryService.findCategory(label);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveCategory(@RequestBody Category Category) {
        return categoryService.saveCategory(Category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category Category, @PathVariable("id") Long id) {
        return categoryService.updateCategory(Category, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("")
    public List<Category> listCategory() {
        return categoryService.listCategory();
    }


}
