package com.example.ProductServiceJan31Capstone.service;

import com.example.ProductServiceJan31Capstone.models.Product;
import org.springframework.stereotype.Service;

public interface ProductService {

    Product getProductById(long id);

}
