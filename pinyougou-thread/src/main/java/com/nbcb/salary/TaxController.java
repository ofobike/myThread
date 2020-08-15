package com.nbcb.salary;

public class TaxController {
    public static void main(String[] args) {
        TaxCalautor taxCalautor = new TaxCalautor(10000d,2000d){
            @Override
            public double calculate() {
                return getSalary()*0.1 + getDonus()*0.15;
            }
        };
        System.out.println(taxCalautor.calculate());
    }
}
