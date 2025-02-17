package com.example.demo.movies.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.movies.client.MovieServiceClient;
import com.example.demo.movies.dto.MovieDTO;
import com.example.demo.movies.entity.CinemaHall;
import com.example.demo.movies.repository.CinemaHallRepository;

class CinemaHallServiceImplTest {

    @Mock
    private CinemaHallRepository cinemaHallRepository;

    @Mock
    private MovieServiceClient movieServiceClient;

    @InjectMocks
    private CinemaHallServiceImpl cinemaHallService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCinemaHallByMovieIdAndSession() {
        CinemaHall mockCinemaHall = new CinemaHall(1L, 1L, "Evening", null, Arrays.asList(1, 2, 3));
        when(cinemaHallRepository.findByMovieIdAndMovieSession(1L, "Evening")).thenReturn(Optional.of(mockCinemaHall));

        Optional<CinemaHall> cinemaHall = cinemaHallService.getCinemaHallByMovieIdAndSession(1L, "Evening");

        assertNotNull(cinemaHall);
    }

    @Test
    void testGetAvailableSeats() {
        CinemaHall mockCinemaHall = new CinemaHall(1L, 1L, "Evening", null, Arrays.asList(1, 2, 3));
        when(cinemaHallRepository.findByMovieIdAndMovieSession(1L, "Evening")).thenReturn(Optional.of(mockCinemaHall));

        MovieDTO mockMovie = new MovieDTO();
        mockMovie.setSeats(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        when(movieServiceClient.getMovieById(1)).thenReturn(mockMovie);

        HashMap<String, Object> availableSeats = cinemaHallService.getAvailableSeats(1L, "Evening", 100, 2);

        assertNotNull(availableSeats);
        assertNotNull(availableSeats.get("availableSeats"));
    }
}
