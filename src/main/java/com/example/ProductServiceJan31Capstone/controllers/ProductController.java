package com.example.ProductServiceJan31Capstone.controllers;

import com.example.ProductServiceJan31Capstone.dtos.ProductResponseDto;
import com.example.ProductServiceJan31Capstone.models.Product;
import com.example.ProductServiceJan31Capstone.service.FakeStoreProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    FakeStoreProductService fakeStoreProductService;

    public ProductController(FakeStoreProductService fakeStoreProductService) {
                this.fakeStoreProductService = fakeStoreProductService;
    }

    // Below can be replaced as @GetMapping
    // @RequestMapping(value = "/Products/{id}", method = RequestMethod.GET,)
    @GetMapping("/Products/{id}")
    public ProductResponseDto getProductByID(@PathVariable long id){

    /*
       // We shouldn't return anything directly to view rather dto.
        ProductResponseDto dummyProductResponseDto = new ProductResponseDto();
        dummyProductResponseDto.setId(1);
        dummyProductResponseDto.setName("product " + id);
        dummyProductResponseDto.setPrice(11.22);
        dummyProductResponseDto.setImageUrl("https://images/url");
        //jackson, a spring library, will convert the object into json and vice-versa.
        return dummyProductResponseDto;
     */

        Product product = fakeStoreProductService.getProductById(id);
        ProductResponseDto productResponseDto = ProductResponseDto.from(product);

        // Had we have not created static from method, we would have used like below
        // ProductResponseDto productResponseDto1 = new ProductResponseDto();
        // productResponseDto1.from(product);
        // return productResponseDto1;

        return productResponseDto;

    }
}
