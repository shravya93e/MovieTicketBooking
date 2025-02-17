package com.example.demo.movies.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.demo.movies.dto.CinemaHallUpdateDTO;
import com.example.demo.movies.entity.CinemaHall;

// Service interface for Cinema Hall operations
public interface CinemaHallService {
    Optional<CinemaHall> getCinemaHallByMovieIdAndSession(Long movieId, String movieSession);  // Method to get a cinema hall by movie ID and session
    List<Integer> updateOccupiedSeats(Long movieId, String movieSession, CinemaHallUpdateDTO updateDTO);  // Method to update occupied seats
    HashMap<String, Object> getAvailableSeats(Long movieId, String movieSession, int money, int count);  // Method to get available seats
}
