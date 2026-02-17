package Week1;

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
