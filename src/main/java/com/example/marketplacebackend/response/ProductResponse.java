package com.example.marketplacebackend.response;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;

    private String image;

    private Double price;

    private String title;

    private String description;


}
