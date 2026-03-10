package com.example.springbootwithjpa.controller;

import com.example.springbootwithjpa.entity.Employee;
import com.example.springbootwithjpa.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class EmployeeController {
    private EmployeeService service;

    EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homePage() {
        return "Welcome to Coditas Employee Management System";
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return service.addEmployee(employee);
    }

    @GetMapping("/findAll")
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("/findCount")
    public Integer findCount() {
        return service.findCount();
    }

    @GetMapping("/getAllDepts")
    public Set<String> getAllDepartments() {
        return service.findAllDepartments();
    }

    @GetMapping("/count/employees/each-dept")
    public List<Object[]> getCountOfEmployeesInEachDept() {
        return service.getCountOfEmployeesInEachDept();
    }

    @GetMapping("/highest-paid")
    public Employee highestPaid() {
        return service.highestPaid();
    }

    @GetMapping("/lowest-paid")
    public Employee lowestPaid() {
        return service.lowestPaid();
    }

    @GetMapping("/employee-salary/greater-than/2000")
    public Integer countOfEmployeesWhoEarnMoreThan2k() {
        return service.countOfEmployeesWhoEarnMoreThan2k();
    }

    @GetMapping("/get/average-salary")
    public Double getAverageSalary() {
        return service.getAverageSalary();
    }

    @GetMapping("/get/top-5/employee/salary")
    public List<Employee> getTop5HighestPaidEmployees() {
        return service.getTop5HighestPaidEmployees();
    }

    @GetMapping("/get/employees/from/marketting")
    public List<Employee> getEmployeesFromMarketingDepartment() {
        return service.getEmployeesFromMarketingDepartment();
    }

    @GetMapping("/get/employee/between")
    public Integer getEmployeesBetween15kAnd25k() {
        return service.getEmployeesBetween15kAnd25k();
    }

    @GetMapping("/get/employees/salary/is-null")
    public List<Employee> getEmployeesWithSalaryAsNull() {
        return service.getEmployeesWithSalaryAsNull();
    }

    @GetMapping("/get/employees/starting-with")
    public List<Employee> getByNameStartingWith() {
        return service.getByNameStartingWith();
    }

    @GetMapping("/get/employee-salaries/desc")
    public List<Double> getSalariesOfAllEmployeesInDesc() {
        return service.getSalariesOfAllEmployeesInDesc();
    }

    @GetMapping("/get/salary/expenditure")
    public Double getSalaryExpenditure() {
        return service.getSalaryExpenditure();
    }

    @GetMapping("/get/employees/same-name")
    public List<String> getEmployeesWithSameFirstName() {
        return service.getEmployeesWithSameFirstName();
    }

    @GetMapping("/get/employees/pune")
    public Integer getEmployeesInPune() {
        return service.getEmployeesInPune();
    }

    @GetMapping("/get/average-salary/employees-dev")
    public Double getAverageSalaryOfDevDept() {
        return service.getAverageSalaryOfDevDept();
    }

    @GetMapping("/get/employees/above-average")
    public List<Employee> getEmployeesAboveAverage() {
        return service.getEmployeesAboveAverage();
    }

    @GetMapping("/get/employees/lowest-salary/test-dept")
    public List<Employee> getEmployeesWithLowestSalaryInTestDepartment() {
        return service.getEmployeesWithLowestSalaryInTestDepartment();
    }

    @GetMapping("/get/salary-expenditure/dev-support")
    public Double totalSalaryExpenditureInDevAndSupport() {
        return service.totalSalaryExpenditureInDevAndSupport();
    }

    @GetMapping("/get/employees/greater-than/average-dev")
    public List<Employee> getEmployeesWithSalaryGreaterThanAverageOfDev() {
        return service.getEmployeesWithSalaryGreaterThanAverageOfDev();
    }

    @GetMapping("/get/salary-expenditure/pune")
    public Double totalSalaryExpenditureInPune() {
        return service.totalSalaryExpenditureInPune();
    }
}
