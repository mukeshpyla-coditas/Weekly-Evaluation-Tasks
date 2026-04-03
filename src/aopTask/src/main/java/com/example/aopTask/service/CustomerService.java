package com.example.aopTask.service;

import com.example.aopTask.entity.Customer;
import com.example.aopTask.entity.Order;
import com.example.aopTask.entity.Product;
import com.example.aopTask.repository.CustomerRepository;
import com.example.aopTask.repository.OrderRepository;
import com.example.aopTask.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Customer placeOrder(Customer customer) {
        Order order = customer.getOrder();
        List<Product> productList = customer.getProductList();
        List<Product> orderProductList = new ArrayList<>();
        List<Product> customerProductList = new ArrayList<>();

        for(Product product : productList) {
            if(product.getOrders() == null) product.setOrders(new ArrayList<>());
            product.getOrders().add(order);
            orderProductList.add(product);
            customerProductList.add(product);
            productRepository.save(product);
        }

        order.setPurchaseDate(LocalDateTime.now());
        order.setProductList(orderProductList);
        orderRepository.save(order);
        customer.setProductList(customerProductList);

        return customerRepository.save(customer);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
