package com.creativityskills.jotech.model;

public enum Money {
    PENNY(0.01), NICKEL(0.05), DIME(0.10), QUARTER(0.25), HALF_DOLLAR(0.50), ONE_DOLLAR(1), TWO_DOLLAR_NOTE(2);
    double dollars;

    Money(double dollars) {
        this.dollars = dollars;
    }

    public double getDollars() {
        return dollars;
    }
}
