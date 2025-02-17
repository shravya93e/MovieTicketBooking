package com.example.demo.service;

import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

@Service  // Marks this class as a Spring service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Constructor for dependency injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user by saving the user details in the database.
     * 
     * @param newUser the user details to be registered
     * @return the saved user entity
     */
    @Override
    public User registerUser(User newUser) {
        return userRepository.save(newUser);
    }

    /**
     * Retrieves a user by email. Throws UserNotFoundException if the user is not found.
     * 
     * @param email the email of the user to be retrieved
     * @return the user entity with the specified email
     * @throws UserNotFoundException if no user is found or if multiple users are found with the same email
     */
    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        try {
            Optional<User> user = userRepository.findByEmail(email);
            return user.orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        } catch (IncorrectResultSizeDataAccessException ex) {
            throw new UserNotFoundException("Multiple users found with email: " + email);
        }
    }
}
