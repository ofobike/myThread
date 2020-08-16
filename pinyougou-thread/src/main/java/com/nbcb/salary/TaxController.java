package com.nbcb.salary;

/**
 * 学习策略模式
 */
public class TaxController {
    public static void main(String[] args) {
       /* TaxCalautor taxCalautor = new TaxCalautor(10000d,2000d){
            @Override
            public double calculate() {
                return getSalary()*0.1 + getDonus()*0.15;
            }
        };
        System.out.println(taxCalautor.calculate());*/
        TaxCalautor taxCalautor = new TaxCalautor(10000d, 2000d);
        CountTaxStrategy countTaxStrategy = new CountTaxStrategyImpl();
        taxCalautor.setCountTaxStrategy(countTaxStrategy);
        double calculate = taxCalautor.calculate();
        System.out.println(calculate);
    }
}
