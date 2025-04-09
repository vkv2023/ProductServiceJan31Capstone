package com.example.ProductServiceJan31Capstone.dtos;

import com.example.ProductServiceJan31Capstone.models.Category;
import com.example.ProductServiceJan31Capstone.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setDescription(description);
        product.setName(title);
        product.setImageUrl(image);
        product.setPrice(price);

        // Category is a model, we are creating the object of the model category
        // setting the name as category
        Category category1 = new Category();
        category1.setName(category);

        product.setCategory(category1);
        return product;
    }

}
