package com.example.marketplacebackend.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class UserDto {

    private String name;

    private String address;

    private String email;

    private String password;

    private Long phone;


}
