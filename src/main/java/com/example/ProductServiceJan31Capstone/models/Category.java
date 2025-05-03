package com.example.ProductServiceJan31Capstone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel implements Serializable {
//  private long id;
//  private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JsonIgnore // When forming result just ignore this fields. and not include products again
    private List<Product> products;
//    We might need below here for featured products
//    @OneToMany(mappedBy = "category")
//    private List<Product> featuredProducts;
}
