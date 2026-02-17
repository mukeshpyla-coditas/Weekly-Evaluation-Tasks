package Week1;

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
