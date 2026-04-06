package com.example.aopTask.service;

import com.example.aopTask.entity.Customer;
import com.example.aopTask.entity.Order;
import com.example.aopTask.entity.Payment;
import com.example.aopTask.entity.Product;
import com.example.aopTask.repository.CustomerRepository;
import com.example.aopTask.repository.OrderRepository;
import com.example.aopTask.repository.PaymentRepository;
import com.example.aopTask.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;

    public Customer placeOrder(Customer customer) {
        Order order = new Order();
        List<Product> productList = customer.getProductList();
        List<Product> orderProductList = new ArrayList<>();
        List<Product> customerProductList = new ArrayList<>();

        for(Product product : productList) {
            Product product1 = productRepository.findById(product.getId()).get();
            log.info(" " + product1);
            product1.getOrders().add(order);
            product1.setPrice(product.getPrice());
            orderProductList.add(product1);
            customerProductList.add(product1);
            productRepository.save(product1);
        }

        order.setPurchaseDate(LocalDateTime.now());
        order.setProductList(orderProductList);
        orderRepository.save(order);

        customer.setProductList(customerProductList);
        customer.setOrder(order);
        return customerRepository.save(customer);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Payment performPayment(Payment payment, Double amount) {
        Customer customer = customerRepository.findById(payment.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Specified customer is not present. Please login before proceeding to payment"));

        Order order = customer.getOrder();
        List<Product> productList = order.getProductList();
        Double totalAmountOfOrder = 0.0;
        for(Product product : productList) {
            totalAmountOfOrder += product.getPrice();
        }

        if(totalAmountOfOrder <= amount) {
            log.info("Balance amount left after transaction: " + (amount - totalAmountOfOrder));
            payment.setTimestamp(new Date());
            paymentRepository.save(payment);
            return payment;
        }
        else throw new RuntimeException("Amount with customer is not sufficient to place the order!");

    }
}
