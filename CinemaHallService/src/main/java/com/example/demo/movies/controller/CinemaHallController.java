package com.example.demo.movies.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.movies.dto.CinemaHallUpdateDTO;
import com.example.demo.movies.entity.CinemaHall;
import com.example.demo.movies.service.CinemaHallService;

import jakarta.validation.Valid;

@RestController  
@Validated  
@RequestMapping("/cinemahalls")  
public class CinemaHallController {

    private final CinemaHallService cinemaHallService;

    // Constructor for dependency injection
    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }
    
    @PutMapping("/api/v1/movie/{movieId}")  
    public ResponseEntity<String> updateOccupiedSeats(@PathVariable Long movieId, @Valid @RequestBody CinemaHallUpdateDTO updateDTO) {
        List<Integer> alreadyBooked=cinemaHallService.updateOccupiedSeats(movieId, updateDTO.getMovieSession(), updateDTO);
          // Returns a response with OK status
        if(alreadyBooked.isEmpty()) {
        	return ResponseEntity.ok("Cinema hall updated successfully");
        }
        
        if(alreadyBooked.size()==updateDTO.getUpdatedSeats().size()) {
        	return ResponseEntity.ok("All the seats are already booked ");
        }
        return ResponseEntity.ok("Cinema hall partially updated and this seats are already booked "+alreadyBooked);
    }

    @GetMapping("/api/v1/movie/{movieId}/{movieSession}")  
    public ResponseEntity<List<Integer>> getUpdatedSeats(@PathVariable Long movieId, @PathVariable String movieSession) {
        Optional<CinemaHall> cinemaHallOptional = cinemaHallService.getCinemaHallByMovieIdAndSession(movieId, movieSession);
        if (cinemaHallOptional.isPresent()) {
            CinemaHall cinemaHall = cinemaHallOptional.get();
            return ResponseEntity.ok(cinemaHall.getUpdatedSeats());  // Returns a response with OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Returns a response with NOT_FOUND status
        }
    }
    
    @GetMapping("/api/v1/getavailableseats/{movieId}/{movieSession}/{money}/{count}")  
    public ResponseEntity<HashMap<String, Object>> getAvailableSeats(@PathVariable Long movieId, @PathVariable String movieSession, @PathVariable int money, @PathVariable int count) {
        HashMap<String, Object> availableSeats = cinemaHallService.getAvailableSeats(movieId, movieSession, money, count);
        return ResponseEntity.ok(availableSeats);  // Always returns a response with OK status and the available seats map
    }
}
