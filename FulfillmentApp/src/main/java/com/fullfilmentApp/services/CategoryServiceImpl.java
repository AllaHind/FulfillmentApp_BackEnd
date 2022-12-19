package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Category;
import com.fullfilmentApp.payload.response.MessageResponse;
import com.fullfilmentApp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findCategory(String label) {
        return categoryRepository.findByLabel(label);
    }

    @Override
    public ResponseEntity<?> saveCategory(Category category) {
        if(findCategory(category.getLabel())!=null)
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Category already exists!"));
        }
        else
            categoryRepository.save(category);
             return  ResponseEntity
                .ok()
                .body(new MessageResponse("Category has been registered successfully!"));

    }

    @Override
    public ResponseEntity<?> updateCategory(Category category, Long id) {

        category.setId(id);
        categoryRepository.save(category);
        return ResponseEntity.ok(new MessageResponse("Category has been registered successfully!"));    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteCategory(Long id) {
        categoryRepository.deleteById(id);

        return ResponseEntity.ok(new MessageResponse("Category has been deleted successfully!"));
    }

    @Override
    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }
}
