package com.example.ProductServiceJan31Capstone.service;

import com.example.ProductServiceJan31Capstone.models.Product;
import org.springframework.data.domain.Page;

public interface SearchService
{
    Page<Product> search(String query, int pageNumber, int pageSize, String sortParam);
}
