package com.example.demo.movies.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Movie ID is required")
    private Long movieId;

    @ElementCollection
    @NotEmpty(message = "Seats are required")
    private List<Integer> seat;
    
    // Set createdAt before persisting
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
