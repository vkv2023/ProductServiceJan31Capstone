package com.example.ProductServiceJan31Capstone.controllers;

import com.example.ProductServiceJan31Capstone.dtos.CreateFakeStoreProductRequestDto;
import com.example.ProductServiceJan31Capstone.dtos.ProductResponseDto;
import com.example.ProductServiceJan31Capstone.models.Product;
import com.example.ProductServiceJan31Capstone.service.FakeStoreProductService;
import com.example.ProductServiceJan31Capstone.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    // FakeStoreProductService fakeStoreProductService;
    ProductService productService;

    public ProductController(FakeStoreProductService productService) {
                this.productService = productService;
    }

/*
    // Below can be replaced as @GetMapping
    // @RequestMapping(value = "/Products/{id}", method = RequestMethod.GET,)
    @GetMapping("/products/{id}")
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

       // place * /

        Product product = fakeStoreProductService.getProductById(id);
        ProductResponseDto productResponseDto = ProductResponseDto.from(product);

        // Had we have not created static from method, we would have used like below
        // ProductResponseDto productResponseDto1 = new ProductResponseDto();
        // productResponseDto1.from(product);
        // return productResponseDto1;

        return productResponseDto;
    }
*/

    //Returning ResponseEntity as HttpStatus OK, ACCEPTED OR any other
    //PathVariable have optional id which is same as used as variable id
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id")
                                                                 long id){

        Product product = productService.getProductById(id);
        ProductResponseDto productResponseDto = ProductResponseDto.from(product);

        ResponseEntity<ProductResponseDto> responseEntity =
                new ResponseEntity<>(productResponseDto, HttpStatus.ACCEPTED);

        return responseEntity;
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for (Product product: products){
            ProductResponseDto productResponseDto = ProductResponseDto.from(product);
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody
                                            CreateFakeStoreProductRequestDto createProductRequestDto){
        Product product =
                productService.createProduct(
                createProductRequestDto.getName(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getCategory(),
                createProductRequestDto.getImageUrl()
                );

        ProductResponseDto productResponseDto = ProductResponseDto.from(product);
        return productResponseDto;
    }


}
