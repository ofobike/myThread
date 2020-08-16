package com.nbcb.salary;

public class TaxCalautor {
    private final double salary;
    private final double donus;

    /**
     * 引入接口数据
     */
    private CountTaxStrategy countTaxStrategy;
    public TaxCalautor(double salary, double donus) {
        this.salary = salary;
        this.donus = donus;
    }

    public double calculate(){
        return this.calcTax();
    }

    private double calcTax() {
        /**
         * 调用接口里的方法
         */
        return countTaxStrategy.calculate(salary,donus);
    }


    public double getSalary() {
        return salary;
    }

    public double getDonus() {
        return donus;
    }

    public CountTaxStrategy getCountTaxStrategy() {
        return countTaxStrategy;
    }

    public void setCountTaxStrategy(CountTaxStrategy countTaxStrategy) {
        this.countTaxStrategy = countTaxStrategy;
    }
}
