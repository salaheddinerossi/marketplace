package com.example.marketplacebackend.serviceImpl;

import com.example.marketplacebackend.dto.UserDto;
import com.example.marketplacebackend.model.User;
import com.example.marketplacebackend.repository.UserRepository;
import com.example.marketplacebackend.response.UserResponse;
import com.example.marketplacebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void createUser(UserDto userDto) {
        User user = new User();
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);

    }

    @Override
    public UserResponse getUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setAddress(user.getAddress());
            userResponse.setEmail(user.getEmail());
            userResponse.setName(user.getName());
            return userResponse;
        }

        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setAddress(user.getAddress());
            userResponse.setEmail(user.getEmail());
            userResponse.setName(user.getName());
            return userResponse;
        }).toList();
    }
}
