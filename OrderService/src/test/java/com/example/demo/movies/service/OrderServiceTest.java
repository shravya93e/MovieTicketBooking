package com.example.demo.movies.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.movies.entity.Order;
import com.example.demo.movies.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setOrderId(1L);
        order.setCustomerId(101L);
        order.setMovieId(1L);
        order.setSeat(List.of(1, 2, 3));
        order.setCreatedAt(new Date());
    }

    @Test
    void testSaveOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        
        Order savedOrder = orderService.saveOrder(order);
        
        assertNotNull(savedOrder);
        assertEquals(order.getOrderId(), savedOrder.getOrderId());
    }

    @Test
    void testGetLastOrderByUserId() {
        when(orderRepository.findFirstByCustomerIdOrderByCreatedAtDesc(101L)).thenReturn(Optional.of(order));
        
        Optional<Order> retrievedOrder = orderService.getLastOrderByUserId(101L);
        
        assertNotNull(retrievedOrder);
        assertEquals(order.getOrderId(), retrievedOrder.get().getOrderId());
    }
}
