package com.mukesh.hibernatetask.service;

import com.mukesh.hibernatetask.entity.Staff;
import com.mukesh.hibernatetask.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    private final StaffRepository repository;

    public StaffService(StaffRepository repository) {
        this.repository = repository;
    }

    // 1. Get all
    public List<Staff> getAllStaff() {
        return repository.findAll();
    }

    // 2. Get by ID
    public Staff getById(int id) {
        return repository.findById(id);
    }

    // 3. Insert
    public Staff saveStaff(Staff staff) {
        return repository.save(staff);
    }

    // 4. Salary > 20000
    public List<Staff> getSalaryAbove(int salary) {
        return repository.findBySalaryGreaterThan(salary);
    }

    // 5. Experience between
    public List<Staff> getExperienceBetween(int min, int max) {
        return repository.findByExperienceBetween(min, max);
    }

    // 6. Max Salary
    public Staff getMaxSalary() {
        return repository.findAll()
                .stream()
                .max((s1, s2) -> s1.getSalary().compareTo(s2.getSalary()))
                .orElseThrow();
    }

    // 7. Update Salary
    public int updateStaffSalary(Integer id, Double amount) {
        return repository.updateStaffSalary(id, amount);
    }

    // 8. Name of Minimum Experience Staff Member
    public Staff findEmployeeWithMinExperience() {
        return repository.findAll()
                .stream()
                .min((s1, s2) -> s1.getExperience().compareTo(s2.getExperience()))
                .orElseThrow();
    }

    // 9. Profile trainer
    public List<Staff> getTrainerProfile() {
        return repository.findByProfile("Trainer");
    }

    // 10. Not Trainer
    public List<Staff> getNonTrainerProfile() {
        return repository.findByProfileNotEquals("Trainer");
    }

}

/*
    package com.mukesh.hibernate2.service;

import com.mukesh.hibernate2.entity.Employee;
import com.mukesh.hibernate2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // findByID()
    public Employee findByID(Integer id) {
        return repository.findById(id);
    }

    // findAll()
    public List<Employee> findAll() {
        return repository.findAll();
    }

    // save()
    public Employee save(Employee employee) { return repository.save(employee); }

    // findEmployeeAboveSalary()
    public List<Employee> findEmployeeAboveSalary(Double amount) {
        return repository.findEmployeeAboveSalary(amount);
    }

    // findEmployeeWithExperienceBetween()
    public List<Employee> findEmployeeWithExperienceBetween(Integer min, Integer max) {
        return repository.findEmployeeWithExperienceBetween(min, max);
    }

    // findEmployeeWithMaxSalary()
    public Employee findEmployeeWithMaxSalary() {
        return repository.findAll()
                .stream()
                .max((s1, s2) -> s1.getSalary().compareTo(s2.getSalary()))
                .orElseThrow();
    }

    // findEmployeeWithMinSalary()
    public Employee findEmployeeWithMinExperience() {
        return repository.findAll()
                .stream()
                .min((s1, s2) -> s1.getExperience().compareTo(s2.getExperience()))
                .orElseThrow();
    }

    // findEmployeeWhoIsManager()
    public List<Employee> findEmployeeWhoIsManager() {
        return repository.findEmployeeWhoIsManager();
    }

    // findEmployeeWhoIsNotManager()
    public List<Employee> findEmployeeWhoIsNotManager() {
        return repository.findEmployeeWhoIsNotManager();
    }

    // updateEmployeeSalary()
    public int updateEmployeeSalary(Integer id, Double amount) {
        return repository.updateEmployeeSalary(id, amount);
    }
}

*/
