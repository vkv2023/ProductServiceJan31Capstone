package com.example.ProductServiceJan31Capstone.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductRequestDto {

    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
