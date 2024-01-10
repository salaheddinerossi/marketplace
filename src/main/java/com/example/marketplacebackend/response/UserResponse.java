package com.example.marketplacebackend.response;


import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String name;

    private String address;

    private String email;

    private Long phone;

}
