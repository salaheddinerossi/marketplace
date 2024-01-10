package com.example.marketplacebackend.response;

import lombok.Data;

@Data
public class ProductUser {
    private Long id;

    private String image;

    private Double price;

    private String title;

    private String description;

    private String name;

    private String address;

    private String email;

    private Long phone;


}
