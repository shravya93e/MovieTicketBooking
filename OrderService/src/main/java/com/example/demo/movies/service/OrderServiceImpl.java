package com.example.demo.movies.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.demo.movies.entity.Order;
import com.example.demo.movies.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service  // Marks this class as a Spring service
@Slf4j  // Enables logging for this class
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    // Constructor for dependency injection
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Saves a new order to the database.
     * 
     * @param newOrder the order details to be saved
     * @return the saved order, or a new empty order if saving fails
     */
    @Override
    public Order saveOrder(Order newOrder) {
        try {
            // Save the new order and return it
            return orderRepository.save(newOrder);
        } catch (Exception e) {
            // Log the error if saving fails and return a new empty Order object
            log.info("order service failure " + e);
            return new Order();
        }
    }

    /**
     * Retrieves the most recent order by user ID.
     * 
     * @param userId the ID of the user whose most recent order is to be retrieved
     * @return the most recent order if found, otherwise an empty Optional
     */
    @Override
    public Optional<Order> getLastOrderByUserId(Long userId) {
        try {
            // Find the most recent order by user ID and return it
            return orderRepository.findFirstByCustomerIdOrderByCreatedAtDesc(userId);
        } catch (Exception e) {
            // Log the error if finding the order fails and return an empty Optional
            log.info("Last order is not found for a user " + e);
            return Optional.empty();
        }
    }
}
