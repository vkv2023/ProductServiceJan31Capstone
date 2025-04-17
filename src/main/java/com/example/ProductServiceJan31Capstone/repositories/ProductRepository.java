package com.example.ProductServiceJan31Capstone.repositories;

import com.example.ProductServiceJan31Capstone.models.Category;
import com.example.ProductServiceJan31Capstone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import com.example.ProductServiceJan31Capstone.customrQuery.CustomQuery;

// JPA Repository
//1st Argument - Table name (entity name)
//2nd Argument - Primary key of the Table (Primitive)
public interface ProductRepository extends JpaRepository<Product, Long> {

     Optional<Product> findById(long id);

     Product save(Product product);

     Void deleteById(long id);

     @Transactional
     @Modifying
     @Query(CustomQuery.UPDATE_IS_DELETED_FLAG)
     int updateIsDeletedById(@Param("id") long id);

     List<Product> findAll();

     // Search by product name (LIKE %name%)
     List<Product> findByNameContainingIgnoreCase(String name);

     // Search by exact category match
     List<Product> findByCategory(Category category);

     // Combined search (name LIKE %name% AND category)
     List<Product> findByNameContainingIgnoreCaseAndCategory(String name, Category category);

/*
     Using QueryMethod to test in getProductById
     We'll comment these methods later after testing.


 //  List<Product> findByCategory(Category category);

     List<Product> findByCategory_Name(String category);

     @Query(CustomQuery.GET_PRODUCTS_FROM_CATEGORY_NAME)
     List<Product> getProductByCategoryName(@Param("categoryName") String categoryName);

     @Query(value = CustomQuery.GET_PRODUCTS_FROM_CATEGORY_NAME_NATIVE, nativeQuery = true)
     List<Product> getProductByCategoryNameNative(@Param("categoryName") String categoryName);
*/

}
