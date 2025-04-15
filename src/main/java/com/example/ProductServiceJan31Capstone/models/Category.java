package com.example.ProductServiceJan31Capstone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
//    private long id;
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private List<Product> products;
//    We might need below here for featured products
//    @OneToMany(mappedBy = "category")
//    private List<Product> featuredProducts;
}
