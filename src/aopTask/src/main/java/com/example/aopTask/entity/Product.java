package com.example.aopTask.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productName;
    private Integer quantity;
    private Double price;

    @ManyToMany(mappedBy = "productList")
    @JsonIgnoreProperties("productList")
    private List<Order> orders = new ArrayList<>();

    @ManyToMany(mappedBy = "productList")
    @JsonIgnoreProperties("productList")
    private List<Customer> customerList = new ArrayList<>();
}
