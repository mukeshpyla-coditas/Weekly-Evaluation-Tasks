package com.example.aopTask.service;

import com.example.aopTask.entity.Order;
import com.example.aopTask.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order cancelOrder(Order order) {
        Order existingOrder = orderRepository.findById(order.getId())
                .orElseThrow(() -> new RuntimeException("Order is cancelled"));

        orderRepository.delete(existingOrder);
        return existingOrder;
    }
}
