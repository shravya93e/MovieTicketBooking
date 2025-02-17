package com.example.demo.movies.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies_table")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    @NotEmpty(message = "Movie name is required")
    private String movieName;

    @NotEmpty(message = "Language is required")
    private String language;

    @Min(value = 0, message = "Price should be greater than or equal to 0")
    private double money;

    @NotEmpty(message = "Movie session is required")
    private String movieSession;

    @ElementCollection
    @NotEmpty(message = "Seats are required")
    private List<Integer> seats;
}
