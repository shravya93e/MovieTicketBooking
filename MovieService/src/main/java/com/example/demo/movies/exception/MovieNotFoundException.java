package com.example.demo.movies.exception;

// Custom exception for when a movie is not found
public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);  // Passes the message to the superclass constructor
    }
}
