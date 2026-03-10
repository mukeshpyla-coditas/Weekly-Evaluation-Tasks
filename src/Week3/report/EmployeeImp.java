package Week3.report;

import Week3.Employee;

import java.util.Arrays;
import java.util.List;

public class EmployeeImp {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Mukesh", 100000);
        Employee emp2 = new Employee("Yaswanth", 200000);
        Employee emp3 = new Employee("Vishal", 150000);

        List<Employee> employeeList = Arrays.asList(emp1, emp2, emp3);

        Employee emp = employeeList.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .get();

        System.out.println("Max Salary: " + emp.getName());
    }
}
