package com.example.demo.movies.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.movies.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query to find the latest order by customer ID
    Optional<Order> findFirstByCustomerIdOrderByCreatedAtDesc(Long customerId);
    
 
}
