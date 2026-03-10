package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e.department, COUNT(e) FROM Employee e GROUP BY e.department")
    List<Object[]> getCountOfEmployeesInEachDept();

    @Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)")
    Employee findHighestPaid();

    @Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MIN(e.salary) FROM Employee e)")
    Employee findLowestPaid();

    Integer countBySalaryGreaterThan(double salary);

    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double getAverageSalary();

    List<Employee> findTop5ByOrderBySalaryDesc();

    List<Employee> findByDepartment(String department);

    List<Employee> findBySalaryBetween(int min, int max);

    List<Employee> findBySalaryIsNull();

    List<Employee> findByNameStartingWith(String startString);

    @Query("SELECT e.salary FROM Employee e ORDER BY e.salary DESC")
    List<Double> getSalariesOfAllEmployeesInDesc();

    @Query("SELECT SUM(e.salary) FROM Employee e")
    Double findTotalSalary();

    @Query("SELECT e.name FROM Employee e GROUP BY e.name HAVING COUNT(e) > 1")
    List<String> getEmployeesWithSameFirstName();

    List<Employee> findByLocation(String location);

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department = 'Dev'")
    Double getAverageSalaryOfDevDept();

    @Query("SELECT e FROM Employee e WHERE e.salary > (SELECT AVG(emp.salary) FROM Employee emp)")
    List<Employee> getEmployeesAboveAverage();

    @Query("SELECT e FROM Employee e WHERE e.department = 'Test' AND e.salary = (SELECT MIN(emp.salary) FROM Employee emp WHERE emp.department = 'Test')")
    List<Employee> getEmployeesWithLowestSalaryInTestDepartment();

    @Query("SELECT SUM(e.salary) FROM Employee e WHERE e.department IN ('Dev', 'Support')")
    Double totalSalaryExpenditureInDevAndSupport();

    @Query("SELECT e FROM Employee e WHERE e.salary > (SELECT AVG(emp.salary) FROM Employee emp WHERE emp.department = 'Dev')")
    List<Employee> getEmployeesWithSalaryGreaterThanAverageOfDev();

    @Query("SELECT SUM(e.salary) FROM Employee e WHERE e.location = 'Pune'")
    Double totalSalaryExpenditureInPune();
}
