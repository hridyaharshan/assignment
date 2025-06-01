package javaassignment;

import java.util.Scanner;

// This class calculates the taxable income and final tax
class TaxCalculator {
    private double salary;
    private int age;
    private double investment;
    private double healthPremium;
    private double homeLoanInterest;


    public TaxCalculator(double salary, int age, double investment, double healthPremium, double homeLoanInterest) {
        this.salary = salary;
        this.age = age;
        this.investment = investment;
        this.healthPremium = healthPremium;
        this.homeLoanInterest = homeLoanInterest;
    }


    public double calculateDeductions() {
        double deduction80C = Math.min(investment, 150000); // Max ₹1.5 lakh
        double deduction80D;

        if (age >= 60) {
            deduction80D = Math.min(healthPremium, 50000); // For senior citizens
        } else {
            deduction80D = Math.min(healthPremium, 25000); // For others
        }

        double deduction24 = Math.min(homeLoanInterest, 200000); // Max ₹2 lakh
        return deduction80C + deduction80D + deduction24;
    }


    public double getTaxableIncome() {
        double deductions = calculateDeductions();
        return Math.max(0, salary - deductions);
    }


    public double calculateTax() {
        double taxableIncome = getTaxableIncome();
        double tax = 0;

        if (age < 60) {
            if (taxableIncome <= 250000) {
                tax = 0;
            } else if (taxableIncome <= 500000) {
                tax = (taxableIncome - 250000) * 0.05;
            } else if (taxableIncome <= 1000000) {
                tax = 250000 * 0.05 + (taxableIncome - 500000) * 0.20;
            } else {
                tax = 250000 * 0.05 + 500000 * 0.20 + (taxableIncome - 1000000) * 0.30;
            }
        } else if (age < 80) {
            if (taxableIncome <= 300000) {
                tax = 0;
            } else if (taxableIncome <= 500000) {
                tax = (taxableIncome - 300000) * 0.05;
            } else if (taxableIncome <= 1000000) {
                tax = 200000 * 0.05 + (taxableIncome - 500000) * 0.20;
            } else {
                tax = 200000 * 0.05 + 500000 * 0.20 + (taxableIncome - 1000000) * 0.30;
            }
        } else {
            if (taxableIncome <= 500000) {
                tax = 0;
            } else if (taxableIncome <= 1000000) {
                tax = (taxableIncome - 500000) * 0.20;
            } else {
                tax = 500000 * 0.20 + (taxableIncome - 1000000) * 0.30;
            }
        }

        return tax;
    }
}


public class IncomeTaxCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========== Income Tax Calculator ===========");


        System.out.print("Enter your annual salary (in INR): ");
        double salary = scanner.nextDouble();

        System.out.print("Enter your age (in years): ");
        int age = scanner.nextInt();

        System.out.print("Enter amount invested in tax-saving instruments (PPF, ELSS, etc.): ");
        double investment = scanner.nextDouble();

        System.out.print("Enter your annual health insurance premium (in INR): ");
        double healthPremium = scanner.nextDouble();

        System.out.print("Enter annual home loan interest paid (in INR): ");
        double homeLoanInterest = scanner.nextDouble();


        TaxCalculator calculator = new TaxCalculator(salary, age, investment, healthPremium, homeLoanInterest);


        double taxableIncome = calculator.getTaxableIncome();
        double tax = calculator.calculateTax();



        System.out.printf("Taxable Income: ₹%.2f\n", taxableIncome);
        System.out.printf("Total Tax Payable: ₹%.2f\n", tax);


        scanner.close();
    }
}

