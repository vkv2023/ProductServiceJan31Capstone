package com.example.ProductServiceJan31Capstone.service;

import com.example.ProductServiceJan31Capstone.exceptions.ProductNotFoundException;
import com.example.ProductServiceJan31Capstone.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productDBService")
// 1- use Primary as Service
//@Primary
public class ProductDBService implements ProductService{
    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(String name, String description, double price, String category, String imageUrl) {
        return null;
    }
}
