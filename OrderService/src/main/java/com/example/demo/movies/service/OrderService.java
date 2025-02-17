package com.example.demo.movies.service;

import java.util.Optional;

import com.example.demo.movies.entity.Order;

public interface OrderService {
    // Save a new order
	Order saveOrder(Order newOrder);

    // Get the last order by user ID
    Optional<Order> getLastOrderByUserId(Long userId);
}
