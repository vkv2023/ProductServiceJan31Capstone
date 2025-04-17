package com.example.ProductServiceJan31Capstone.dtos;

import com.example.ProductServiceJan31Capstone.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    // we need to change Category to String, as we need not want to know outside world
    // about the category as String, private Category category;
    private String category;

    // we are using static as we don't want to create a new object
    public static ProductResponseDto from(Product product){

        if (product == null){
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategory(product.getCategory().getName());

        return productResponseDto;
    }
}
