package com.example.ProductServiceJan31Capstone.controllers;

import com.example.ProductServiceJan31Capstone.dtos.CreateFakeStoreProductRequestDto;
import com.example.ProductServiceJan31Capstone.dtos.ErrorDto;
import com.example.ProductServiceJan31Capstone.dtos.ProductResponseDto;
import com.example.ProductServiceJan31Capstone.exceptions.ProductNotFoundException;
import com.example.ProductServiceJan31Capstone.models.Product;
import com.example.ProductServiceJan31Capstone.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
There are 2 ProductService, now ProductController to decide which service to call first,
productDbService or fakeStoreProductService
1- Either mark 1 service as primary
2- Name each service and use @qualifier="productDBService" in ProductController constructor
*/

@RestController
public class ProductController {

    // FakeStoreProductService fakeStoreProductService;
    ProductService productService;

    public ProductController(@Qualifier("productDBService")
            ProductService productService) {
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
//    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
    public ProductResponseDto getProductById(@PathVariable("id") long id) throws ProductNotFoundException {

        Product product = productService.getProductById(id); // will mock this method in Test
        ProductResponseDto productResponseDto = ProductResponseDto.from(product); // from() is of ProductResponseDto that's why we are testing this line only.

//        ResponseEntity<ProductResponseDto> responseEntity =
//                new ResponseEntity<>(productResponseDto, HttpStatus.ACCEPTED);
//
//        return responseEntity;
        return productResponseDto;
    }

    //List all products

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        // List<ProductResponseDto> productResponseDtoList1 = products.
        //        stream().map(ProductResponseDto::from)
        //        .collect(Collectors.toUnmodifiableList());

        for (Product product: products){
            ProductResponseDto productResponseDto = ProductResponseDto.from(product);
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }

    //Search products

    @GetMapping("/products/search")
    public ResponseEntity<List<ProductResponseDto>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {

        List<Product> products = productService.searchProducts(name, category);
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for (Product product: products){
            ProductResponseDto productResponseDto = ProductResponseDto.from(product);
            productResponseDtoList.add(productResponseDto);
        }

        return ResponseEntity.ok(productResponseDtoList);
    }

    //Create a new Product
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

    // Delete Product Physically or Marking Soft Delete
    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<Void> deleteProductByID(@PathVariable long id) throws EmptyResultDataAccessException, ProductNotFoundException {
        Optional<Product> product = Optional.ofNullable(productService.getProductById(id));

        if (product.isPresent()){
            // If we need to DELETE the product physically

            // productService.deleteProduct(id);

            // If we need to Mark the isDeleted FLag as True
            productService.updateIsDeletedById(id);
            return ResponseEntity.noContent().build(); //204 No Content
        }
        return ResponseEntity.noContent().build(); // Not Found
    }

    // For handling the NUllPointerExceptions in Product Controller
    // Creating a new Global Exception Handler in exception package
    @ExceptionHandler(NullPointerException.class)
    public ErrorDto handleNullPointerException(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Null pointer Exception..");
        errorDto.setStatus("Failure");
        return errorDto;
    }

}
