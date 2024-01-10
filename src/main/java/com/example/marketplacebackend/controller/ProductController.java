package com.example.marketplacebackend.controller;


import com.example.marketplacebackend.dto.ProductDto;
import com.example.marketplacebackend.model.Product;
import com.example.marketplacebackend.service.ProductService;
import com.example.marketplacebackend.service.UserService;
import com.example.marketplacebackend.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto,@AuthenticationPrincipal UserDetails userDetails){

        if(!SecurityUtils.isAuthenticated(userDetails.getAuthorities())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("you are not authorized to do this action");
        }


        System.out.println(userService.getUser(userDetails.getUsername()));
        productDto.setUserId(userService.getUser(userDetails.getUsername()).getId());
        productService.createProduct(productDto);
        return  ResponseEntity.ok("productCreated");

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){

        return ResponseEntity.ok(productService.getProduct(id));

    }

    @GetMapping("/seller")
    public ResponseEntity<?> getProductBySeller(@AuthenticationPrincipal UserDetails userDetails){

        if(!SecurityUtils.isAuthenticated(userDetails.getAuthorities())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("you are not authorized to do this action");
        }


        return ResponseEntity.ok(productService.getProductsByUser(userDetails.getUsername()));

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllProducts(){

        return ResponseEntity.ok(productService.getAllProducts());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id,@AuthenticationPrincipal UserDetails userDetails){


        productService.deleteProduct(id,userDetails.getUsername());
        return ResponseEntity.ok("account deleted");
    }





}
