import java.util.*;

/*
    Concrete Methods in Parent Class:
    => attendanceDeduction()
    => taxCalculation()
    => getNameOfClass()
    => pfCalculation()
    => netSalaryCalculation()
    => performanceBonusCalculation()

    Abstract Methods in Parent Class:
    => grossSalaryCalculation()

    Constants in the Parent Class:
    => PF_RATE - 0.12 (declared as static final)
*/

abstract class Employee {
    protected double baseSalary;
    protected int attendanceDays;
    protected int ratingOfEmployee;
    protected static final double PF_RATE = 0.12;

    Employee(double baseSalary, int attendanceDays, int ratingOfEmployee) {
        this.baseSalary = baseSalary;
        this.attendanceDays = attendanceDays;
        this.ratingOfEmployee = ratingOfEmployee;
    }

    public String getNameOfClass() {
        return this.getClass().getName();
    }

    public double attendanceDeduction() {
        double dailySalary = baseSalary / 30;
        int absentDays = 30 - attendanceDays;
        double attendanceDeduction = absentDays * dailySalary;

        return attendanceDeduction;
    }

    public double taxCalculation() {
        double taxableIncome = grossSalaryCalculation() + performanceBonusCalculation();
        int taxRate = 0;

        if(taxableIncome <= 50000) taxRate = 5;
        else if(taxableIncome > 50000 && taxableIncome <= 100000) taxRate = 10;
        else if(taxableIncome > 100000 && taxableIncome <= 150000) taxRate = 15;
        else if(taxableIncome > 150000) taxRate = 20;

        double tax = taxableIncome * (taxRate / 100);

        return tax;
    }

    public int getBonusPercentage() {
        int bonusPercentage = 0;
        switch(this.ratingOfEmployee) {
            case 1:
                bonusPercentage = 0;
                break;

            case 2:
                bonusPercentage = 5;
                break;

            case 3:
                bonusPercentage = 10;
                break;

            case 4:
                bonusPercentage = 15;
                break;

            case 5:
                bonusPercentage = 20;
                break;

            default:
                System.out.println("Rating of the employee must be in the range of [1, 5]! Please try again.");
                break;
        }

        return bonusPercentage;
    }

    public double performanceBonusCalculation() {
        return grossSalaryCalculation() * (getBonusPercentage() / 100.0);
    }

    public double pfCalculation() {
        return baseSalary * PF_RATE;
    }

    public double netSalaryCalculation() {
        double netSalary = grossSalaryCalculation() + performanceBonusCalculation() - taxCalculation() - pfCalculation() - attendanceDeduction();
        return netSalary;
    }

    abstract double grossSalaryCalculation();

}

class Developer extends Employee {
    float overTimeHours;

    Developer(double baseSalary, int attendanceDays, int ratingOfEmployee, float overTimeHours) {
        super(baseSalary, attendanceDays, ratingOfEmployee);
        this.overTimeHours = overTimeHours;
    }

    // GrossSalary = BaseSalary + (OvertimeHours * 500)
    public double grossSalaryCalculation() {
        double grossSalary = baseSalary + (overTimeHours * 500);
        return grossSalary;
    }

}

class Manager extends Employee {
    int teamSize;

    Manager(double baseSalary, int attendanceDays, int ratingOfEmployee, int teamSize) {
        super(baseSalary, attendanceDays, ratingOfEmployee);
        this.teamSize = teamSize;
    }

    // GrossSalary = BaseSalary + (TeamSize * 1000)
    public double grossSalaryCalculation() {
        double grossSalary = baseSalary + (teamSize * 1000);
        return grossSalary;
    }
}


class Intern extends Employee {
    Intern(double baseSalary, int attendanceDays, int ratingOfEmployee) {
        super(baseSalary, attendanceDays, ratingOfEmployee);
    }

    // AttendancePercent = (AttendanceDays / 30) * 100
    public double grossSalaryCalculation() {
        double attendancePercentage = (attendanceDays / 30) * 100;
        double grossSalary = baseSalary;
        // GrossSalary = BaseSalary - (BaseSalary * 0.20)
        if(attendancePercentage < 70) {
            grossSalary = baseSalary - (baseSalary * 0.20);
        }

        return grossSalary;
    }
}

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
