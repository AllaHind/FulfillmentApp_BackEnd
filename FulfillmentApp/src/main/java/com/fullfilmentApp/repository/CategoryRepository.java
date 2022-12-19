package com.fullfilmentApp.repository;

import com.fullfilmentApp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByLabel(String label);
}
