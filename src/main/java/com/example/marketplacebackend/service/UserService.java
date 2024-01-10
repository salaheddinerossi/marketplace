package com.example.marketplacebackend.service;

import com.example.marketplacebackend.dto.UserDto;
import com.example.marketplacebackend.response.UserResponse;

import java.util.List;

public interface UserService {


    public void createUser(UserDto user);

    public UserResponse getUser(String email);

    public List<UserResponse> getAllUsers();


}
