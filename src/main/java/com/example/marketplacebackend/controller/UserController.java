package com.example.marketplacebackend.controller;

import com.example.marketplacebackend.dto.LoginDto;
import com.example.marketplacebackend.dto.UserDto;
import com.example.marketplacebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok("account created");

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
