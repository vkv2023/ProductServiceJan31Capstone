package com.example.ProductServiceJan31Capstone.repositories;

import com.example.ProductServiceJan31Capstone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA Repository
//1st Argument - Table name (entity name)
//2nd Argument - Primary key of the Table (Primitive)
public class ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
}
