package com.nbcb.salary;

/**
 * 抽象一个接口
 */
public interface CountTaxStrategy {

    /**
     * 定义接口
     * @param salary
     * @param bonux
     * @return
     */
   public double calculate(double salary,double bonux);
}
