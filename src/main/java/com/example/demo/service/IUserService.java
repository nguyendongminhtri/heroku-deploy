package com.example.demo.service;

import com.example.demo.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Optional<User> findById(Long id);
    Iterable<User> findUsersByNameContaining(String user_name);
    User save(User user);
}
