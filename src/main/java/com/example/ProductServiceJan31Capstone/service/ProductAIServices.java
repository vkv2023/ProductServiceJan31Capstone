package com.example.ProductServiceJan31Capstone.service;

import com.example.ProductServiceJan31Capstone.models.Product;

public interface ProductAIServices {

    Product createProductWithAIDescription(String name, double price, String imageUrl, String category);
}
