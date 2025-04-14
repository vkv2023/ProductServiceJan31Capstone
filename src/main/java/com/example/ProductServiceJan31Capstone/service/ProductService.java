package com.example.ProductServiceJan31Capstone.service;

import com.example.ProductServiceJan31Capstone.exceptions.ProductNotFoundException;
import com.example.ProductServiceJan31Capstone.models.Product;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public interface ProductService {

    Product getProductById(long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(String name, String description, double price, String category, String imageUrl);
    List<Product> searchProducts(String name, String category);
    Void deleteProduct(long id) throws EmptyResultDataAccessException;
}
