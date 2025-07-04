package com.example.ProductServiceJan31Capstone.service;

import com.example.ProductServiceJan31Capstone.exceptions.ProductNotFoundException;
import com.example.ProductServiceJan31Capstone.models.Category;
import com.example.ProductServiceJan31Capstone.models.Product;
import com.example.ProductServiceJan31Capstone.repositories.CategoryRepository;
import com.example.ProductServiceJan31Capstone.repositories.ProductRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@Service("productDBService")
// 1- use Primary as Service
//@Primary
public class ProductDBService implements ProductService, ProductAIServices{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    private final ChatClient chatClient;

    public ProductDBService(ProductRepository productRepository,
                            CategoryRepository categoryRepository,
                            ChatClient chatClient) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.chatClient = chatClient;
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

        Optional<Product> optionalProduct = productRepository.findById(id); // mock this function

        if (optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product with Id " + id + " is not present.");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // mock this test
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

        // In order to ensure Category is available before product
        // create a category if not available, if available then return it.

        // Category categoryObj = new Category();
        // categoryObj.setName(category);

        Category categoryObj = getCategoryFromDB(category); // mock this function if null or not

        product.setCategory(categoryObj);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product createProductWithAIDescription(
            String name, double price, String imageUrl, String category){

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        String description = getDescriptionFromAI(product);
        product.setDescription(description);

        // In order to ensure Category is available before product
        // create a category if not available, if available then return it.

        // Category categoryObj = new Category();
        // categoryObj.setName(category);

        Category categoryObj = getCategoryFromDB(category); // mock this function if null or not

        product.setCategory(categoryObj);

        return productRepository.save(product);
    }

    private String getDescriptionFromAI(Product product){
        String messagePrompt = String.format("Generate description for a product name '%s' " +
                        ", the price $%.2f of the product and given the imageUrl is '%s" +
                        "as and the category is '%s', please limit response to 200 words.",
                product.getName().toLowerCase(),product.getPrice(), product.getImageUrl(), product.getCategory());

        return chatClient.prompt().user(messagePrompt).call().content();

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
