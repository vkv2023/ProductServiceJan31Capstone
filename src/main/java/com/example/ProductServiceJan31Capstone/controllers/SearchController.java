package com.example.ProductServiceJan31Capstone.controllers;

import com.example.ProductServiceJan31Capstone.dtos.ProductResponseDto;
import com.example.ProductServiceJan31Capstone.dtos.SearchRequestDto;
import com.example.ProductServiceJan31Capstone.models.Product;
import com.example.ProductServiceJan31Capstone.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {

    SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    // SearchQuery using POST and using ProductResponseDto

    @PostMapping("/searchQuery")
    public Page<ProductResponseDto> searchRequest(@RequestBody SearchRequestDto searchRequestDto){
        Page<Product> productsPage = searchService.search(searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(),
                searchRequestDto.getPageSize(),
                searchRequestDto.getSortParam());

        return getProductResponseDtoFromPage(productsPage);
    }

    // SearchQuery using GET

    @GetMapping("/searchQuery")
    public Page<ProductResponseDto> searchRequest(@RequestParam String query, int pageNumber,
                                       int pageSize, String sortParam){
        Page<Product> productsPage =
                searchService.search(query, pageNumber, pageSize, sortParam);

        return getProductResponseDtoFromPage(productsPage);
    }

    private Page<ProductResponseDto> getProductResponseDtoFromPage(Page<Product> productsPage){

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        List<Product> products = productsPage.getContent();

        for(Product product: products){
            ProductResponseDto productResponseDto =
                    ProductResponseDto.from(product);
            productResponseDtos.add(productResponseDto);
        }

        return new PageImpl<>(productResponseDtos,productsPage.getPageable(),
                productsPage.getTotalElements());
    }

// SearchQuery using POST and using Product model directly

//    @PostMapping("/searchQuery")
//    public Page<Product> searchRequest(@RequestBody SearchRequestDto searchRequestDto){
//
//        Page<Product> products = searchService.search(searchRequestDto.getQuery(),
//                searchRequestDto.getPageNumber(),
//                searchRequestDto.getPageSize(),
//                searchRequestDto.getSortParam());
//
//        return products;
//    }

}
