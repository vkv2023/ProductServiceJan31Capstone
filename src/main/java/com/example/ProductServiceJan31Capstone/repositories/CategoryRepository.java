package com.example.ProductServiceJan31Capstone.repositories;

import com.example.ProductServiceJan31Capstone.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    Category save(Category category);
}
