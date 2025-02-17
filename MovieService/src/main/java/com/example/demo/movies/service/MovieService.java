package com.example.demo.movies.service;

import java.util.List;

import com.example.demo.movies.entity.Movies;
import com.example.demo.movies.exception.MovieNotFoundException;

// Service interface for Movie operations
public interface MovieService {
    String addMovie(Movies movies);  // Method to add a movie
    Movies getMovie(int movieId) throws MovieNotFoundException;  // Method to get a movie by ID
    List<Movies> getAllMovies();  // Method to get all movies
    String deleteMovie(int movieId);  // Method to delete a movie by ID
    Movies updateMovie(Movies movies);  // Method to update a movie
}
