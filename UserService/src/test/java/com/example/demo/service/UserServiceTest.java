package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginUser() {
        User mockUser = new User(1L, "John", "Doe", "john@example.com", "password");
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(mockUser));

        User user = userService.getUserByEmail("john@example.com");

        assertNotNull(user);  // Assert that the user is not null
        assertEquals("john@example.com", user.getEmail());  // Assert that the email matches
    }

    @Test
    void testRegisterUser() {
        User newUser = new User("Jane", "password123");
        User savedUser = new User(2L, "Jane", null, "jane@example.com", "password123");
        
        when(userRepository.save(newUser)).thenReturn(savedUser);
        
        User user = userService.registerUser(newUser);

        assertNotNull(user);  // Assert that the user is not null
        assertEquals("Jane", user.getName());  // Assert that the name matches
        assertEquals("password123", user.getPassword());  // Assert that the password matches
    }

}
