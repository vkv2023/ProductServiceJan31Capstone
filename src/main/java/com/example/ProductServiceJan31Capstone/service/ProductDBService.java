package com.example.ProductServiceJan31Capstone.service;

import com.example.ProductServiceJan31Capstone.exceptions.ProductNotFoundException;
import com.example.ProductServiceJan31Capstone.models.Category;
import com.example.ProductServiceJan31Capstone.models.Product;
import com.example.ProductServiceJan31Capstone.repositories.CategoryRepository;
import com.example.ProductServiceJan31Capstone.repositories.ProductRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    /*
          Using getProductByID to test Queries written in CustomQuery Class
          and below 4 methods define in ProductDbService
    */

//        1- Method to test
//        Optional<Category> optionalCategory = categoryRepository.findByName("Laptop");
//        List<Product> product = productRepository.findByCategory(optionalCategory.get());

        //to Fetch Lazy or use previous one
//      List<Product> product = optionalCategory.get().getProducts();

//        2- Method to test
//        List<Product> product = productRepository.findByCategory_Name("Laptop");

//        3- Method to test
//        List<Product> product = productRepository.getProductByCategoryName("Laptop");

//        4- Method to test
//        List<Product> product = productRepository.getProductByCategoryNameNative("Laptop");

//        System.out.println(product);

//        return null;

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product with Id " + id + " is not present.");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
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

    public List<Product> searchProducts(String name, String category) {

        Category categoryObj = getCategoryFromDB(category);

        if (name != null && category != null) {
            return productRepository.findByNameContainingIgnoreCaseAndCategory(name, categoryObj);
        } else if (name != null) {
            return productRepository.findByNameContainingIgnoreCase(name);
        } else if (categoryObj != null) {
            return productRepository.findByCategory(categoryObj);
        }
        return productRepository.findAll();
    }
/*
    Testing with Postman
    Successful Deletion: DELETE /products/delete/123 → 204 No Content
    Non-existent Product: DELETE /products/delete/999 → 404 Not Found
*/
    //To ensure atomic operations we use @Transactional
    @Override
    @Transactional
    public Void deleteProduct(long id) throws EmptyResultDataAccessException {
        productRepository.deleteById(id);
        return null;
    }

    @Override
    @Transactional
    public int updateIsDeletedById(long id) throws EmptyResultDataAccessException{
        productRepository.updateIsDeletedById(id);
        return 1;
    }

}
