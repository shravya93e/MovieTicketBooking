package com.example.demo.movies.client;

import com.example.demo.movies.dto.MovieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client interface for communicating with the Movie Service.
 * This interface defines methods to call end points of the Movie Service.
 */
@FeignClient(name = "movie-service", url = "http://localhost:8012")  
public interface MovieServiceClient {
    
    /**
     * Calls the Movie Service to get a movie by its ID.
     * @param movieId the ID of the movie to retrieve
     * @return a MovieDTO object containing movie details
     */
    @GetMapping("movies/getById/{id}")
    MovieDTO getMovieById(@PathVariable("id") int movieId);
}
