package com.example.marketplacebackend.repository;

import com.example.marketplacebackend.model.Product;
import com.example.marketplacebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>{
    List<Product> findByUser(User user);
}
