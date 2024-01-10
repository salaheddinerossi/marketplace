package com.example.marketplacebackend.dto;

import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class ProductDto {

    private String image;

    private Double price;

    private String title;

    private String description;

    private Long userId;

}
