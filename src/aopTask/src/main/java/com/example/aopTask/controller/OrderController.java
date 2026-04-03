package com.example.aopTask.controller;

import com.example.aopTask.entity.Order;
import com.example.aopTask.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/cancel")
    public Order cancelOrder(@RequestBody Order order) {
        return orderService.cancelOrder(order);
    }
}
