// src/main/java/com/example/demo/service/impl/UserServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User register(User user) {
    Optional<User> existing = userRepository.findByEmail(user.getEmail());
    if (existing.isPresent()) {
      throw new RuntimeException("User email exists");
    }
    if (user.getRole() == null) {
      user.setRole("STAFF");
    }
    user.setPassword(encoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElse(null);
  }
}
