package com.example.ProductServiceJan31Capstone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
//    private long id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
//    We might need below here for featured products
//    @OneToMany(mappedBy = "category")
//    private List<Product> featuredProducts;
}
