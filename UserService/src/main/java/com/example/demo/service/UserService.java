package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;

public interface UserService {
    // Method to register a new user
    User registerUser(User newUser);

    // Method to fetch a user by email
    User getUserByEmail(String email) throws UserNotFoundException;
}
