package Week1;

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
