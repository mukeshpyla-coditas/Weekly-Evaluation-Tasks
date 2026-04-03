package com.example.aopTask.controller;

import com.example.aopTask.entity.Customer;
import com.example.aopTask.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/place-order")
    public Customer placeOrder(@RequestBody Customer customer) {
        return customerService.placeOrder(customer);
    }

    @GetMapping("/getAll")
    public List<Customer> getAllCustomer() {
        return customerService.getAll();
    }
}
