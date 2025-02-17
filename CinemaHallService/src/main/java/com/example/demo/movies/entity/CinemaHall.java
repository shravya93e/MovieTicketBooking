package com.example.demo.movies.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cinema_hall")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Movie ID is required")
    private Long movieId;

    @NotEmpty(message = "Movie session is required")
    private String movieSession;

    private String orderTime;

    @ElementCollection //Specifies a collection of basic or embeddable types.
//    @NotEmpty(message = "Updated seats are required")
    private List<Integer> updatedSeats;
}
