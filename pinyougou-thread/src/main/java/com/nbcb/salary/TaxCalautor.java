package com.nbcb.salary;

public class TaxCalautor {
    private final double salary;
    private final double donus;

    public TaxCalautor(double salary, double donus) {
        this.salary = salary;
        this.donus = donus;
    }

    public double calculate(){
        return this.calcTax();
    }

    private double calcTax() {
        return 0.0d;
    }


    public double getSalary() {
        return salary;
    }

    public double getDonus() {
        return donus;
    }
}
