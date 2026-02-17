package Week1;

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
