package com.example.demo.movies.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.movies.entity.Movies;
import com.example.demo.movies.service.MovieServiceImpl;

import jakarta.validation.Valid;

@RestController  
@Validated  
@RequestMapping("/movies")  // Maps requests to /movies
public class MoviesController {

	private final MovieServiceImpl moviesService;

    // Constructor for dependency injection
    public MoviesController(MovieServiceImpl moviesService) {
        this.moviesService = moviesService;
    }

    @PostMapping("/addmovie")  // Maps POST requests to /addmovie
    public ResponseEntity<String> saveMovie(@Valid @RequestBody Movies movies) {
        moviesService.addMovie(movies);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie added successfully");  // Returns a response with CREATED status
    }

    @GetMapping("/getById/{id}")  
    public ResponseEntity<Movies> getMovie(@PathVariable("id") int movieId) {
        Movies movie = moviesService.getMovie(movieId);
        return ResponseEntity.ok(movie);  // Returns a response with OK status
    }

    @GetMapping("/getAll")  // Maps GET requests to /getAll
    public ResponseEntity<List<Movies>> getAllMovies() {
        List<Movies> movies = moviesService.getAllMovies();
        return ResponseEntity.ok(movies);  // Returns a response with OK status
    }

    @DeleteMapping("/deleteById/{id}")  
    public ResponseEntity<String> deleteMovie(@PathVariable("id") int movieId) {
        moviesService.deleteMovie(movieId);
        return ResponseEntity.ok("Movie deleted successfully");  // Returns a response with OK status
    }

    @PutMapping("/update")  // Maps PUT requests to /update
    public ResponseEntity<Movies> update(@Valid @RequestBody Movies movie) {
        Movies updatedMovie = moviesService.updateMovie(movie);
        return ResponseEntity.ok(updatedMovie);  // Returns a response with OK status
    }
}
