package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService 
{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) 
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) 
    {
        if (user.getRole() == null) 
        {
            user.setRole("STAFF"); [cite: 41, 221]
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) 
        {
            throw new RuntimeException("Email already exists"); [cite: 223]
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); [cite: 40, 222]
        return userRepository.save(user); [cite: 224]
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null); [cite: 225-227]
    }
}