package com.example.ProductServiceJan31Capstone.controllers;

import com.example.ProductServiceJan31Capstone.dtos.ProductResponseDto;
import com.example.ProductServiceJan31Capstone.exceptions.ProductNotFoundException;
import com.example.ProductServiceJan31Capstone.models.Category;
import com.example.ProductServiceJan31Capstone.models.Product;
import com.example.ProductServiceJan31Capstone.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ProductControllerTest {

    @MockitoBean
    @Qualifier("productDBService")
    public ProductService productService;

    @Autowired
    public ProductController productController;

    @Test
    public void testProductByIdReturnsProductResponseDto() throws ProductNotFoundException {

        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setName("name");
        dummyProduct.setDescription("description");
        dummyProduct.setPrice(22.23);
        dummyProduct.setImageUrl("imageUrl");

        Category dummyCategory = new Category();

        dummyCategory.setId(1L);
        dummyCategory.setName("category");
        dummyProduct.setCategory(dummyCategory);

        when(productService.getProductById(1L)).thenReturn(dummyProduct);

        ProductResponseDto productResponseDto = productController.getProductById(1L);

        assertEquals(1L,productResponseDto.getId());
        assertEquals("name",productResponseDto.getName());
        assertEquals("description",productResponseDto.getDescription());
        assertEquals(22.23,productResponseDto.getPrice());
        assertEquals("imageUrl",productResponseDto.getImageUrl());
        assertEquals("category",productResponseDto.getCategory());

    }

    @Test
    public void testProductByIdReturnsNull() throws ProductNotFoundException {

        when(productService.getProductById(1L)).thenReturn(null);
        ProductResponseDto productResponseDto = productController.getProductById(1L);
        // Can also bes used
        // assertNull(productResponseDto);
        assertEquals(null, productResponseDto);
    }

}