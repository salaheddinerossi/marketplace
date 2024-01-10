package com.example.marketplacebackend.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private Double price;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
