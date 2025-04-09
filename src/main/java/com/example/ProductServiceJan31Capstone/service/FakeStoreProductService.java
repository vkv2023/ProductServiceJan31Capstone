package com.example.ProductServiceJan31Capstone.service;

import com.example.ProductServiceJan31Capstone.dtos.FakeStoreProductDto;
import com.example.ProductServiceJan31Capstone.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) {
    // restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeProductServiceImpl.class);
      FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
              "https://fakestoreapi.com/products/" + id,
              FakeStoreProductDto.class);
    // 1- way to getting the result
    //        Product product = new Product();
    //        product.setId(fakeStoreProductDto.getId());
    // 2- call createFromDto()
    // 3- got to dto and create toProduct()

        return fakeStoreProductDto.toProduct();
    }

    // 2- another way to create
    // private Product createFromDto(){
    //        Product product = new Product();
    //        product.setId(fakeStoreProductDto.getId());
    //        return getProductById();
    //}
}
