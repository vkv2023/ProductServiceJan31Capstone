package com.example.ProductServiceJan31Capstone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
//    private long id;
//    private String name;
    private String description;
    private String imageUrl;
    private double price;
    @ManyToOne
    private Category category;
}
