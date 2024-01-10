package com.example.marketplacebackend.service;

import com.example.marketplacebackend.dto.ProductDto;
import com.example.marketplacebackend.response.ProductResponse;

import java.util.List;

public interface ProductService {

    public void createProduct(ProductDto productDto);

    public void deleteProduct(Long id,String email);

    public ProductResponse getProduct(Long id);

    public List<ProductResponse> getAllProducts();

    public List<ProductResponse> getProductsByUser(String email);


}
