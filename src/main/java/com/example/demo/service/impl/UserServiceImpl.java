package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.encoder = new BCryptPasswordEncoder();
  }

  @Override
  public User register(User user) {
    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
      throw new RuntimeException("Student email exists"); // required keyword per constraints
    }
    String role = (user.getRole() == null || user.getRole().isBlank()) ? "STAFF" : user.getRole();
    user.setRole(role);
    user.setPassword(encoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElse(null);
  }
}
