package com.example.ProductServiceJan31Capstone.service;

import com.example.ProductServiceJan31Capstone.dtos.FakeStoreProductDto;
import com.example.ProductServiceJan31Capstone.dtos.FakeStoreProductRequestDto;
import com.example.ProductServiceJan31Capstone.exceptions.ProductNotFoundException;
import com.example.ProductServiceJan31Capstone.models.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FakeStoreProductServiceTest {

    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    RedisTemplate<String, Object> redisTemplate = Mockito.mock(RedisTemplate.class);

    FakeStoreProductService fakeStoreProductService =
            new FakeStoreProductService(restTemplate, redisTemplate);

    @Test
    public void testGetProductByIdReturnProduct() throws ProductNotFoundException {

        FakeStoreProductDto dummyFakeStoreProductDto =
                new FakeStoreProductDto();
        dummyFakeStoreProductDto.setId(1L);
        dummyFakeStoreProductDto.setTitle("title");
        dummyFakeStoreProductDto.setPrice(1.0);
        dummyFakeStoreProductDto.setDescription("description");
        dummyFakeStoreProductDto.setImage("image");
        dummyFakeStoreProductDto.setCategory("category");

        when(restTemplate.getForObject("https://fakestoreapi.com/products/1",
                FakeStoreProductDto.class)).thenReturn(dummyFakeStoreProductDto);

        Product product = fakeStoreProductService.getProductById(1L);

        assertEquals(1L,product.getId());
        assertEquals("title",product.getName());

    }

    @Test
    public void testGetProductByIdWithNullPointerException() throws ProductNotFoundException{

        when(restTemplate.getForObject("https://fakestoreapi.com/products/1",
                FakeStoreProductDto.class)).thenReturn(null);

        // Product product = fakeStoreProductService.getProductById(1L);
        // Act and assert together
        assertThrows(ProductNotFoundException.class,
                () -> fakeStoreProductService.getProductById(1L));
    }

    @Test
    public void testCreateProductReturnsProductWithId(){

        FakeStoreProductDto dummyFakeStoreProductDto =
                new FakeStoreProductDto();
        dummyFakeStoreProductDto.setId(1L);
        dummyFakeStoreProductDto.setTitle("title");
        dummyFakeStoreProductDto.setPrice(1.0);
        dummyFakeStoreProductDto.setDescription("description");
        dummyFakeStoreProductDto.setImage("image");
        dummyFakeStoreProductDto.setCategory("category");
/*
        FakeStoreProductRequestDto fakeStoreProductRequestDto =
                new FakeStoreProductRequestDto();
        fakeStoreProductRequestDto.setTitle("title");

        // fakeStoreProductRequestDto is not same we need to use another way of using any()
        when(restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreProductRequestDto,
                FakeStoreProductDto.class)).thenReturn(dummyFakeStoreProductDto);

*/
        // we are using matchers here
        // use eq()
        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreProductDto.class))).thenReturn(dummyFakeStoreProductDto);

        Product product = fakeStoreProductService.createProduct("title","description",
                21.21,"category","imageUrl");

                assertEquals(1L, product.getId());
                assertEquals("title", product.getName());
    }

    @Test
    public void testCreateProductVerifyAPICalls(){

        FakeStoreProductDto dummyFakeStoreProductDto =
                new FakeStoreProductDto();
        dummyFakeStoreProductDto.setId(1L);
        dummyFakeStoreProductDto.setTitle("title");
        dummyFakeStoreProductDto.setPrice(1.0);
        dummyFakeStoreProductDto.setDescription("description");
        dummyFakeStoreProductDto.setImage("image");
        dummyFakeStoreProductDto.setCategory("category");

        // we are using matchers here
        // use eq()
        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreProductDto.class))).thenReturn(dummyFakeStoreProductDto);

        Product product = fakeStoreProductService.createProduct("title","description",
                21.21,"category","imageUrl");

        // To verify how many times a method was called
        // change it to 2 or 3 from 1-> it should fail
        verify(restTemplate, times(1)).postForObject(
                eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreProductDto.class));
    }

}