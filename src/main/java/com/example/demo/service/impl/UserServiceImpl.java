package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserDTO> getAll() {
        return repository.findAll().stream()
                .map(u -> new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO create(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword("hashed"); // replace with real hashing
        user.setRole(dto.getRole() != null ? dto.getRole() : "STAFF");

        User saved = repository.save(user);
        return new UserDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getRole());
    }
}
