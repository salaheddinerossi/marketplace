package com.example.marketplacebackend.controller;

import com.example.marketplacebackend.dto.LoginDto;
import com.example.marketplacebackend.response.JwtResponse;
import com.example.marketplacebackend.response.UserResponse;
import com.example.marketplacebackend.secutiry.JwtTokenUtil;
import com.example.marketplacebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Authentication {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody  LoginDto loginDto) {



        try {

            final UserResponse user = userService.getUser(loginDto.getEmail());
            authenticate(loginDto.getEmail(), loginDto.getPassword());
            final String token = jwtTokenUtil.generateToken(loginDto.getEmail(), "ROLE");
            return ResponseEntity.ok(new JwtResponse(token,user.getId(), user.getName(),"user"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private void authenticate(String email, String password) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

}
