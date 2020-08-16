package com.nbcb.salary;

public class CountTaxStrategyImpl implements CountTaxStrategy {
    private final double SALARY_STARE = 0.1;
    private final double BONUX_STARE = 0.1;

    @Override
    public double calculate(double salary, double bonux) {
        return salary*SALARY_STARE + bonux * BONUX_STARE;
    }
}
