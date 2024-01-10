package com.example.marketplacebackend.serviceImpl;

import com.example.marketplacebackend.dto.ProductDto;
import com.example.marketplacebackend.model.Product;
import com.example.marketplacebackend.model.User;
import com.example.marketplacebackend.repository.ProductRepository;
import com.example.marketplacebackend.repository.UserRepository;
import com.example.marketplacebackend.response.ProductResponse;
import com.example.marketplacebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void createProduct(ProductDto productDto) {
        Product product = new Product();
        User user = userRepository.findById(productDto.getUserId()).isPresent() ? userRepository.findById(productDto.getUserId()).get() : null;
        product.setUser(user);
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        product.setImage(productDto.getImage());

        productRepository.save(product);

    }

    @Override
    public void deleteProduct(Long id,String email) {

        Product product = productRepository.findById(id).isPresent() ? productRepository.findById(id).get() : null;
        if (product != null) {
            if(!Objects.equals(product.getUser().getEmail(), email)){

                throw new RuntimeException();
            }
            productRepository.delete(product);
        }

    }

    @Override
    public ProductResponse getProduct(Long id) {

        Product product = productRepository.findById(id).isPresent() ? productRepository.findById(id).get() : null;
        if (product != null) {
            return getProductResponse(product);
        }
        return null;
    }

    private ProductResponse getProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setDescription(product.getDescription());
        productResponse.setId(product.getId());
        productResponse.setImage(product.getImage());
        productResponse.setPrice(product.getPrice());
        productResponse.setTitle(product.getTitle());
        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();
        return getProductResponses(products);

    }

    @Override
    public List<ProductResponse> getProductsByUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            List<Product> products = productRepository.findByUser(user);
            return getProductResponses(products);
        }
        return null;
    }

    private List<ProductResponse> getProductResponses(List<Product> products) {
        return products.stream().map(this::getProductResponse).toList();
    }
}
