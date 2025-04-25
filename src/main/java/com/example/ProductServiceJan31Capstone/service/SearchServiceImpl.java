package com.example.ProductServiceJan31Capstone.service;

import com.example.ProductServiceJan31Capstone.dtos.ProductResponseDto;
import com.example.ProductServiceJan31Capstone.models.Product;
import com.example.ProductServiceJan31Capstone.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    ProductRepository productRepository;

    public SearchServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> search(String query,
                                           int pageNumber,
                                           int pageSize,
                                           String sortParam){

        // List<Product> product = productRepository.findByNameContaining(query);

        // By default, its ascending and sort is
        Sort sort = Sort.by(sortParam).descending();
//                .and(Sort.by("price").ascending())
//                .and(Sort.by("id").ascending());

        Pageable pageAble = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> products = productRepository.findByNameContaining(query, pageAble);
        return products;
    }
}
