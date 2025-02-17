package com.example.demo.movies.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.movies.client.MovieServiceClient;
import com.example.demo.movies.dto.CinemaHallUpdateDTO;
import com.example.demo.movies.dto.MovieDTO;
import com.example.demo.movies.entity.CinemaHall;
import com.example.demo.movies.repository.CinemaHallRepository;

@Service  // Marks this class as a Spring service
public class CinemaHallServiceImpl implements CinemaHallService {

    private final CinemaHallRepository cinemaHallRepository;
    private final MovieServiceClient movieServiceClient;

    // Constructor for dependency injection
    public CinemaHallServiceImpl(CinemaHallRepository cinemaHallRepository, MovieServiceClient movieServiceClient) {
        this.cinemaHallRepository = cinemaHallRepository;
        this.movieServiceClient = movieServiceClient;
    }

    /**
     * Retrieves a cinema hall by movie ID and session.
     * 
     * @param movieId the ID of the movie
     * @param movieSession the session of the movie
     * @return an Optional containing the cinema hall, if found
     */
    @Override
    public Optional<CinemaHall> getCinemaHallByMovieIdAndSession(Long movieId, String movieSession) {
        return cinemaHallRepository.findByMovieIdAndMovieSession(movieId, movieSession);  // Returns a cinema hall by movie ID and session
    }

    /**
     * Updates the occupied seats for a specific movie session.
     * 
     * @param movieId the ID of the movie
     * @param movieSession the session of the movie
     * @param updateDTO the DTO containing the updated seats and order time
     * @return a list of already booked seats
     */
    @Override
    public List<Integer> updateOccupiedSeats(Long movieId, String movieSession, CinemaHallUpdateDTO updateDTO) {
        
        List<Integer> alreadyBooked = new ArrayList<>();  // List to hold already booked seats
        List<Integer> notBooked = new ArrayList<>();  // List to hold not booked seats
        Optional<CinemaHall> cinemaHallOptional = cinemaHallRepository.findByMovieIdAndMovieSession(movieId, movieSession);
        
        CinemaHall cinemaHall = cinemaHallOptional.orElseGet(CinemaHall::new);  // Get existing cinema hall or create a new one

        cinemaHall.setMovieId(movieId);
        cinemaHall.setMovieSession(movieSession);

        // Fetch the current list of booked seats and update it with new bookings
        List<Integer> currentBookedSeats = cinemaHall.getUpdatedSeats();
        if (currentBookedSeats == null) {
            currentBookedSeats = new ArrayList<>();
        }
        
        boolean flag = checkSeats(currentBookedSeats, updateDTO.getUpdatedSeats(), alreadyBooked, notBooked);  // Check seats availability
        
        if (updateDTO.getUpdatedSeats() != null) {
            currentBookedSeats.addAll(notBooked);  // Add not booked seats to the current booked seats
        }

        cinemaHall.setUpdatedSeats(currentBookedSeats);
        if (updateDTO.getOrderTime() != null)
            cinemaHall.setOrderTime(updateDTO.getOrderTime());

        cinemaHallRepository.save(cinemaHall);  // Saves the updated cinema hall
        return alreadyBooked;
    }

    /**
     * Retrieves the available seats for a specific movie session and calculates the total amount.
     * 
     * @param movieId the ID of the movie
     * @param movieSession the session of the movie
     * @param money the price per seat
     * @param count the number of seats
     * @return a map containing the total amount, message, and available seats
     */
    @Override
    public HashMap<String, Object> getAvailableSeats(Long movieId, String movieSession, int money, int count) {
        Optional<CinemaHall> cinemaHallOptional = cinemaHallRepository.findByMovieIdAndMovieSession(movieId, movieSession);
        HashMap<String, Object> response = new HashMap<>();
        if (cinemaHallOptional.isPresent()) {
            CinemaHall cinemaHall = cinemaHallOptional.get();

            // Fetch the movie details to get the total seats
            MovieDTO movie = movieServiceClient.getMovieById(movieId.intValue());
            List<Integer> totalSeats = new ArrayList<>(movie.getSeats());

            List<Integer> availableSeats = new ArrayList<>(totalSeats);
            availableSeats.removeAll(cinemaHall.getUpdatedSeats());

            int totalAmount = money * count;  // Calculate total amount
            String msg = "Order booked successfully";

            response.put("totalAmount", totalAmount);
            response.put("msg", msg);
            response.put("availableSeats", availableSeats);

            return response;
        }

        // Return a map with a meaningful message if cinema hall is not found
        response.put("msg", "Cinema hall not found for the given movie ID and session");
        response.put("availableSeats", new ArrayList<>());
        response.put("totalAmount", 0);
        return response;
    }
    
    /**
     * Checks the availability of seats and separates already booked and not booked seats.
     * 
     * @param AllSeats the list of all seats
     * @param seats the list of seats to be checked
     * @param alreadyBooked the list to hold already booked seats
     * @param notBooked the list to hold not booked seats
     * @return true if no seats are already booked, otherwise false
     */
    public boolean checkSeats(List<Integer> AllSeats, List<Integer> seats, List<Integer> alreadyBooked, List<Integer> notBooked) {
        
        for (int seat : seats) {
            if (AllSeats.contains(seat)) {
                alreadyBooked.add(seat);  // Add to already booked seats
            } else {
                notBooked.add(seat);  // Add to not booked seats
            }
        }
        
        return alreadyBooked.isEmpty();
    }
}
