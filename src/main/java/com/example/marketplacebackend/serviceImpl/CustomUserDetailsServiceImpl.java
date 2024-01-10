package com.example.marketplacebackend.serviceImpl;

import com.example.marketplacebackend.repository.UserRepository;
import com.example.marketplacebackend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        com.example.marketplacebackend.model.User user = userRepository.findByEmail(username);

        if(user!=null){
            return new User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE"))
            );
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
