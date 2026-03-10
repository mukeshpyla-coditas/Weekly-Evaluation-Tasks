package com.example.springbootwithjpa.service;

import com.example.springbootwithjpa.entity.Employee;
import com.example.springbootwithjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository repository;

    @Autowired
    EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    //  1. What are the names of all employees?
    public List<Employee> findAll() {
        return repository.findAll();
    }

    // 2. How many employees are there in total?
    public Integer findCount() {
        return repository.findAll().size();
    }

    // 3. What departments do the employees work in?
    public Set<String> findAllDepartments() {
        List<Employee> employeeList = repository.findAll();
        Set<String> departments = new HashSet<>();
        for(Employee employee : employeeList) {
            departments.add(employee.getDepartment());
        }

        return departments;
    }

    // 4. How many employees work in each department?
    public List<Object[]> getCountOfEmployeesInEachDept() {
        return repository.getCountOfEmployeesInEachDept();
    }

    // 5. Who is the highest-paid employee?
    public Employee highestPaid() {
        return repository.findHighestPaid();
    }

    // 6. Who is the lowest-paid employee?
    public Employee lowestPaid() {
        return repository.findLowestPaid();
    }

    // 7. How many employees earn more than RS 20000 per year?
    public Integer countOfEmployeesWhoEarnMoreThan2k() {
        return repository.countBySalaryGreaterThan(2000);
    }

    //  8. What is the average salary of all employees?
    public Double getAverageSalary() {
        return repository.getAverageSalary();
    }

    // 9. Who are the top 5 highest-paid employees?
    public List<Employee> getTop5HighestPaidEmployees() {
        return repository.findTop5ByOrderBySalaryDesc();
    }

    // 10. Who are the employees in the Marketing department?
    public List<Employee> getEmployeesFromMarketingDepartment() {
        return repository.findByDepartment("Marketing");
    }

    // 11. How many employees have a salary between RS 15000 and RS 25000?
    public Integer getEmployeesBetween15kAnd25k() {
        return repository.findBySalaryBetween(15000, 25000).size();
    }

    // 12. Who are the employees with a salary of NULL?
    public List<Employee> getEmployeesWithSalaryAsNull() {
        return repository.findBySalaryIsNull();
    }

    // 13. Who are the employees with a first name starting with "J"?
    public List<Employee> getByNameStartingWith() {
        return repository.findByNameStartingWith("J");
    }

    //  14. What are the salaries of all employees sorted in descending order?
    public List<Double> getSalariesOfAllEmployeesInDesc() {
        return repository.getSalariesOfAllEmployeesInDesc();
    }

    //  15. What is the total salary expenditure of the company?
    public Double getSalaryExpenditure() {
        return repository.findTotalSalary();
    }

    //  16. Who are the employees with the same first names?
    public List<String> getEmployeesWithSameFirstName() {
        return repository.getEmployeesWithSameFirstName();
    }

    // 17. How many employees are there in the Pune location?
    public Integer getEmployeesInPune() {
        return repository.findByLocation("Pune").size();
    }

    //  18. What is the average salary of employees in the Dev department?
    public Double getAverageSalaryOfDevDept() {
        return repository.getAverageSalaryOfDevDept();
    }

    //  19. Who are the employees with salaries above the average?
    public List<Employee> getEmployeesAboveAverage() {
        return repository.getEmployeesAboveAverage();
    }

    // 20. Who are the employees with the lowest salary in the Test department?
    public List<Employee> getEmployeesWithLowestSalaryInTestDepartment() {
        return repository.getEmployeesWithLowestSalaryInTestDepartment();
    }

    // 21st and 22nd API calls are based on year which is not in the table schema provided

    //  23. What is the total salary expenditure in the Dev and Support department?
    public Double totalSalaryExpenditureInDevAndSupport() {
        return repository.totalSalaryExpenditureInDevAndSupport();
    }

    // 24. Who are the employees with a salary greater than the average salary of the Dev department?
    public List<Employee> getEmployeesWithSalaryGreaterThanAverageOfDev() {
        return repository.getEmployeesWithSalaryGreaterThanAverageOfDev();
    }

    // 25. What is the total salary expenditure in Pune location?
    public Double totalSalaryExpenditureInPune() {
        return repository.totalSalaryExpenditureInPune();
    }
}
