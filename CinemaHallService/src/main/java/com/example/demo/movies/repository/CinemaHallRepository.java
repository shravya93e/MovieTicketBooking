package com.example.demo.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.movies.entity.CinemaHall;

public interface CinemaHallRepository extends JpaRepository<CinemaHall, Long> {
	// Custom query to find a cinema hall by movie ID and session
    Optional<CinemaHall> findByMovieIdAndMovieSession(Long movieId, String movieSession);  
}
