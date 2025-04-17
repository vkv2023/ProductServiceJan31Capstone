package com.example.ProductServiceJan31Capstone.customrQuery;

public class CustomQuery {

    public static final String GET_PRODUCTS_FROM_CATEGORY_NAME =
            "SELECT p FROM Product p where p.category.name= :categoryName";
    public static final String GET_PRODUCTS_FROM_CATEGORY_NAME_NATIVE =
            "SELECT * FROM Product p where category_id in (select id from Category where name= :categoryName)";
    public static final String UPDATE_IS_DELETED_FLAG =
            "UPDATE Product p SET p.isDeleted = true, p.lastModifiedAt = CURRENT_TIMESTAMP WHERE p.id = :id";
    //            "UPDATE Product p SET p.isDeleted = true WHERE p.id = :id";
}
