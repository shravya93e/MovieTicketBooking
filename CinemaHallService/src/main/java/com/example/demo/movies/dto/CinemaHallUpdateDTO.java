package com.example.demo.movies.dto;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CinemaHallUpdateDTO {

    @NotEmpty(message = "Movie session is required")
    private String movieSession;

    private String orderTime;

    @NotEmpty(message = "Updated seats are required")
    private List<Integer> updatedSeats;
}
