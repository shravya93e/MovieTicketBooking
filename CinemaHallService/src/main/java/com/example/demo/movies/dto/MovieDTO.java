package com.example.demo.movies.dto;

import java.util.List;

import lombok.Data;

@Data
public class MovieDTO {
    private int movieId;
    private String movieName;
    private String language;
    private int money;
    private String movieSession;
    private List<Integer> seats;

}
