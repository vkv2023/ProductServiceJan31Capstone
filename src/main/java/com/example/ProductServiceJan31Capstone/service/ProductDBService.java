package com.example.ProductServiceJan31Capstone.service;

import com.example.ProductServiceJan31Capstone.exceptions.ProductNotFoundException;
import com.example.ProductServiceJan31Capstone.models.Category;
import com.example.ProductServiceJan31Capstone.models.Product;
import com.example.ProductServiceJan31Capstone.repositories.CategoryRepository;
import com.example.ProductServiceJan31Capstone.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productDBService")
// 1- use Primary as Service
//@Primary
public class ProductDBService implements ProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public ProductDBService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(String name, String description,
                                 double price, String category, String imageUrl) {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        // In order to ensure Category is available beofre product
        // create a category if not available, if available then return it.

        // Category categoryObj = new Category();
        // categoryObj.setName(category);

        Category categoryObj = getCategoryFromDB(category);

        product.setCategory(categoryObj);
        return productRepository.save(product);
    }

    private Category getCategoryFromDB(String name){

        Optional<Category> optionalCategory = categoryRepository.findByName(name);

        if (optionalCategory.isPresent()){
            return optionalCategory.get();
        }

        Category newCategory = new Category();
        newCategory.setName(name);

        return categoryRepository.save(newCategory);
    }
}
