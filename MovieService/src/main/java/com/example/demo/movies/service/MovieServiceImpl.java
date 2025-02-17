package com.example.demo.movies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.movies.entity.Movies;
import com.example.demo.movies.exception.MovieNotFoundException;
import com.example.demo.movies.repository.MovieRepository;

@Service  // Marks this class as a Spring service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepo;

    // Constructor for dependency injection
    public MovieServiceImpl(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    /**
     * Adds a new movie to the database.
     * 
     * @param movies the movie details to be added
     * @return a success message if the movie is saved, otherwise a failure message
     */
    @Override
    public String addMovie(Movies movies) {
        Movies savedMovie = movieRepo.save(movies);
        if (savedMovie.getMovieId() != 0) { // Checks if the movie has been assigned an ID
            return "Movie Saved Successfully"; // Returns success message if movie is saved
        } else {
            return "Failed To Save Movie"; // Returns failure message if movie is not saved
        }
    }

    /**
     * Retrieves a movie by its ID. Throws MovieNotFoundException if the movie is not found.
     * 
     * @param movieId the ID of the movie to be retrieved
     * @return the movie entity with the specified ID
     * @throws MovieNotFoundException if no movie is found with the given ID
     */
    @Override
    public Movies getMovie(int movieId) throws MovieNotFoundException {
        Optional<Movies> optional = movieRepo.findById(movieId);
        return optional.orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + movieId)); // Throws exception if movie is not found
    }

    /**
     * Retrieves a list of all movies from the database.
     * 
     * @return a list of all movies
     */
    @Override
    public List<Movies> getAllMovies() {
        return movieRepo.findAll(); // Returns a list of all movies
    }

    /**
     * Deletes a movie by its ID.
     * 
     * @param movieId the ID of the movie to be deleted
     * @return a success message if the movie is deleted
     */
    @Override
    public String deleteMovie(int movieId) {
        movieRepo.deleteById(movieId);
        return "Movie Deleted Successfully"; // Returns success message if movie is deleted
    }

    /**
     * Updates the details of an existing movie.
     * 
     * @param movies the movie details to be updated
     * @return the updated movie entity, or null if the update fails
     */
    @Override
    public Movies updateMovie(Movies movies) {
        Movies updatedMovie = movieRepo.save(movies);
        if (updatedMovie.getMovieId() != 0) { // Checks if the movie has been assigned an ID
            return updatedMovie; // Returns the updated movie
        } else {
            return null; // Returns null if movie is not updated
        }
    }
}
