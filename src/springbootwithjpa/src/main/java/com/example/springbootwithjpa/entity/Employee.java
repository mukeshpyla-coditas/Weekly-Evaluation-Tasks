package com.example.springbootwithjpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double salary;

    private String department;

    private String location;

    public Employee(String name, Double salary, String department, String location) {
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.location = location;
    }
}
