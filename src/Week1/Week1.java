package Week1;

import java.util.Scanner;

public class Week1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        double baseSalary = 0;
        int totalAttendedDays = 0;
        int ratingOfEmployee = 0;

        while(flag) {
            System.out.println("WELCOME TO CODITAS EMPLOYEE PAYROLL SYSTEM!!");
            System.out.println("Please select the employee type: ");
            System.out.println("1 -> Developer\n2 -> Manager\n3 -> Intern");
            System.out.print("Enter your choice: ");
            int choice = s.nextInt();
            s.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the base salary of the employee: ");
                    baseSalary = s.nextDouble();
                    System.out.print("Enter the number of days employee has attended in total: ");
                    totalAttendedDays = s.nextInt();
                    System.out.print("Enter the rating of the employee: ");
                    ratingOfEmployee = s.nextInt();
                    System.out.print("Enter number of over-time hours employee has worked: ");
                    float overTimeHoursWorked = s.nextFloat();

                    Employee developer = new Developer(baseSalary, totalAttendedDays, ratingOfEmployee, overTimeHoursWorked);
                    printDetails(developer);
                    break;

                case 2:
                    System.out.print("Enter the base salary of the employee: ");
                    baseSalary = s.nextDouble();
                    System.out.print("Enter the number of days employee has attended in total: ");
                    totalAttendedDays = s.nextInt();
                    System.out.print("Enter the rating of the employee: ");
                    ratingOfEmployee = s.nextInt();
                    System.out.print("Enter the team size: ");
                    int teamSize = s.nextInt();

                    Employee manager = new Manager(baseSalary, totalAttendedDays, ratingOfEmployee, teamSize);
                    printDetails(manager);
                    break;

                case 3:
                    System.out.print("Enter the base salary of the employee: ");
                    baseSalary = s.nextDouble();
                    System.out.print("Enter the number of days employee has attended in total: ");
                    totalAttendedDays = s.nextInt();
                    System.out.print("Enter the rating of the employee: ");
                    ratingOfEmployee = s.nextInt();

                    Employee intern = new Intern(baseSalary, totalAttendedDays, ratingOfEmployee);
                    printDetails(intern);
                    break;

                default:
                    System.out.println("Please enter valid option to select valid employee type.");
                    break;
            }

            System.out.print("Do you want to continue? (1 -> YES ; 0 -> NO): ");
            int val = s.nextInt();

            if(val == 0) flag = false;
        }
    }

    public static void printDetails(Employee employeeObject) {
        System.out.println("Employee Type: " + employeeObject.getNameOfClass());
        System.out.printf("Gross Salary: %.2f", employeeObject.grossSalaryCalculation());
        System.out.println();
        System.out.printf("Attendance Deduction: %.2f", employeeObject.attendanceDeduction());
        System.out.println();
        System.out.printf("Bonus: %.2f", employeeObject.performanceBonusCalculation());
        System.out.println();
        System.out.printf("PF: %.2f", employeeObject.pfCalculation());
        System.out.println();
        System.out.printf("Tax: %.2f", employeeObject.taxCalculation());
        System.out.println();
        System.out.printf("Net Salary: %.2f", employeeObject.netSalaryCalculation());
        System.out.println();
    }
}
