package com.example.ProductServiceJan31Capstone.repositories;

import com.example.ProductServiceJan31Capstone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// JPA Repository
//1st Argument - Table name (entity name)
//2nd Argument - Primary key of the Table (Primitive)
public interface ProductRepository extends JpaRepository<Product, Long> {

     Optional<Product> findById(long id);
     List<Product> findAll();
     Product save(Product product);
//     List<Product> findProductsLike(String name);
}
