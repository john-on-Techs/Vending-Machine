package com.creativityskills.jotech.ejb;

import com.creativityskills.jotech.model.Money;

import java.util.Map;

public interface CalculatorI {

    double calculate(int twoDollarNote, int oneDollarNote, int halfDollar, int quarters, int dimes, int nickels, int pennies);

    double calculateBalance(double productPrice, double userMoney);

    boolean canMachineGiveBalance(double machineMoney, double balance);

    //calculate the number of each type to return;
    Map<Money, Integer> calculateNumberOfEachTypeToReturn(double balance);


}
