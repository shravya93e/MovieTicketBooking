package com.example.demo.movies.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.movies.entity.Movies;
import com.example.demo.movies.repository.MovieRepository;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    public MovieServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMovie() {
        Movies mockMovie = new Movies(1, "Inception", "English", 100, "Evening", null);
        when(movieRepository.findById(1)).thenReturn(Optional.of(mockMovie));

        Movies movie = movieService.getMovie(1);

        assertNotNull(movie);
        assertEquals(1, movie.getMovieId());
        assertEquals("Inception", movie.getMovieName());
    }

    @Test
    void testAddMovie() {
        Movies mockMovie = new Movies(1, "Inception", "English", 100, "Evening", null);
        when(movieRepository.save(mockMovie)).thenReturn(mockMovie);

        String response = movieService.addMovie(mockMovie);

        assertEquals("Movie Saved Successfully", response);
    }

    @Test
    void testGetAllMovies() {
        Movies mockMovie1 = new Movies(1, "Inception", "English", 100, "Evening", null);
        Movies mockMovie2 = new Movies(2, "Interstellar", "English", 150, "Morning", null);
        List<Movies> mockMovies = Arrays.asList(mockMovie1, mockMovie2);
        when(movieRepository.findAll()).thenReturn(mockMovies);

        List<Movies> movies = movieService.getAllMovies();

        assertNotNull(movies);
        assertEquals(2, movies.size());
    }

    @Test
    void testDeleteMovie() {
        doNothing().when(movieRepository).deleteById(1);

        String response = movieService.deleteMovie(1);

        assertEquals("Movie Deleted Successfully", response);
    }

    @Test
    void testUpdateMovie() {
        Movies mockMovie = new Movies(1, "Inception", "English", 100, "Evening", null);
        when(movieRepository.save(mockMovie)).thenReturn(mockMovie);

        Movies updatedMovie = movieService.updateMovie(mockMovie);

        assertNotNull(updatedMovie);
        assertEquals(1, updatedMovie.getMovieId());
        assertEquals("Inception", updatedMovie.getMovieName());
    }
}
