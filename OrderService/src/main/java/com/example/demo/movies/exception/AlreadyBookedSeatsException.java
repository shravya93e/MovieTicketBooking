package com.example.demo.movies.exception;

import java.util.List;

public class AlreadyBookedSeatsException extends RuntimeException {
    private List<Integer> bookedSeats;

    public AlreadyBookedSeatsException(List<Integer> bookedSeats) {
        super("Seats already booked: " + bookedSeats);
        this.bookedSeats = bookedSeats;
    }

    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }
}
