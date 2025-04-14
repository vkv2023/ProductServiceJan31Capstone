package com.example.ProductServiceJan31Capstone.service;

import com.example.ProductServiceJan31Capstone.dtos.FakeStoreProductDto;
import com.example.ProductServiceJan31Capstone.dtos.FakeStoreProductRequestDto;
import com.example.ProductServiceJan31Capstone.exceptions.ProductNotFoundException;
import com.example.ProductServiceJan31Capstone.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStore")
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
    // restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeProductServiceImpl.class);
      FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
              "https://fakestoreapi.com/products/" + id,
              FakeStoreProductDto.class);
        // 1- way to getting the result
        //        Product product = new Product();
        //        product.setId(fakeStoreProductDto.getId());
        // 2- call createFromDto()
        // 2- another way to create outside this scope and within the scope of class
        // private Product createFromDto(){
        //        Product product = new Product();
        //        product.setId(fakeStoreProductDto.getId());
        //        return getProductById();
        //}
        // 3- got to dto and create toProduct()

        if (fakeStoreProductDto == null){
            throw  new ProductNotFoundException("The product for id " + id + " is not available..");
        }

        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        // Due to typeEraser in List<E>, we can't use list FakeStoreProductDto
        // thus we are using Array and will convert into Array
        FakeStoreProductDto[] fakeStoreProductDtos
                = restTemplate.getForObject("https://fakestoreapi.com/products",
                  FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){
            Product product = fakeStoreProductDto.toProduct();
            products.add(product);
        }
        return products;
    }

    @Override
    public Product createProduct(String name, String description, double price, String category, String imageUrl) {

        FakeStoreProductRequestDto fakeStoreProductRequestDto =
                new FakeStoreProductRequestDto();
        fakeStoreProductRequestDto.setTitle(name);
        fakeStoreProductRequestDto.setPrice(price);
        fakeStoreProductRequestDto.setDescription(description);
        fakeStoreProductRequestDto.setCategory(category);
        fakeStoreProductRequestDto.setImage(imageUrl);

        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductRequestDto,
                FakeStoreProductDto.class);

        return fakeStoreProductDto.toProduct();
    }

    public List<Product> searchProducts(String name, String category) {
        FakeStoreProductDto[] fakeStoreProductDtos
                = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            Product product = fakeStoreProductDto.toProduct();
            products.add(product);
        }
        return products;
    }

    @Override
    public Void deleteProduct(long id) {
        return null;
    }
}
