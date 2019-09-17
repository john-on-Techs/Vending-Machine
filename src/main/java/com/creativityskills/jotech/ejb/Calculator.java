package com.creativityskills.jotech.ejb;

import com.creativityskills.jotech.model.Money;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class Calculator implements CalculatorI {
    @EJB
    VendingMachine vendingMachine;

    @Override
    public double calculate(int twoDollarNote, int oneDollarNote, int halfDollar, int quarters, int dimes, int nickels, int pennies) {
        return twoDollarNote * Money.TWO_DOLLAR_NOTE.getDollars() +
                oneDollarNote * Money.ONE_DOLLAR.getDollars() +
                halfDollar * Money.HALF_DOLLAR.getDollars() +
                quarters * Money.QUARTER.getDollars() +
                dimes * Money.DIME.getDollars() +
                nickels * Money.NICKEL.getDollars() +
                pennies * Money.PENNY.getDollars();
    }

    @Override
    public double calculateBalance(double productPrice, double userMoney) {
        return userMoney - productPrice;
    }

    @Override
    public boolean canMachineGiveBalance(double machineMoney, double balance) {
        return machineMoney >= balance;
    }

    @Override
    public Map<Money, Integer> calculateNumberOfEachTypeToReturn(double amount) {
        Map<Money, Integer> changes = Collections.emptyMap();
        if (amount > 0) {
            double balance = amount;
            changes = new HashMap<>();
            while (balance > 0) {
                if (balance >= Money.TWO_DOLLAR_NOTE.getDollars() && vendingMachine.getMoneys().get(Money.TWO_DOLLAR_NOTE) > 0) {
                    int count =0 ;
                    if(changes.get(Money.TWO_DOLLAR_NOTE) != null){
                      count = changes.get(Money.ONE_DOLLAR);
                    }
                    changes.put(Money.TWO_DOLLAR_NOTE, count + 1);
                    balance = balance - Money.TWO_DOLLAR_NOTE.getDollars();
                } else if (balance >= Money.ONE_DOLLAR.getDollars() && vendingMachine.getMoneys().get(Money.ONE_DOLLAR) > 0) {
                    int count =0 ;
                    if(changes.get(Money.ONE_DOLLAR) != null){
                        count = changes.get(Money.ONE_DOLLAR);
                    }
                    changes.put(Money.ONE_DOLLAR, count + 1);
                    balance = balance - Money.ONE_DOLLAR.getDollars();
                } else if (balance >= Money.HALF_DOLLAR.getDollars() && vendingMachine.getMoneys().get(Money.HALF_DOLLAR) > 0) {
                    int count =0 ;
                    if(changes.get(Money.HALF_DOLLAR) != null){
                        count = changes.get(Money.HALF_DOLLAR);
                    }
                    changes.put(Money.HALF_DOLLAR, count + 1);
                    balance = balance - Money.HALF_DOLLAR.getDollars();;
                } else if (balance >= Money.QUARTER.getDollars() && vendingMachine.getMoneys().get(Money.QUARTER) > 0) {
                    int count =0 ;
                    if(changes.get(Money.QUARTER) != null){
                        count = changes.get(Money.QUARTER);
                    }
                    changes.put(Money.QUARTER, count + 1);
                    balance = balance - Money.QUARTER.getDollars();

                } else if (balance >= Money.DIME.getDollars() && vendingMachine.getMoneys().get(Money.DIME) > 0) {
                    int count =0 ;
                    if(changes.get(Money.DIME) != null){
                        count = changes.get(Money.DIME);
                    }
                    changes.put(Money.DIME, count + 1);
                    balance = balance - Money.DIME.getDollars();

                } else if (balance >= Money.NICKEL.getDollars() && vendingMachine.getMoneys().get(Money.NICKEL) > 0) {
                    int count =0 ;
                    if(changes.get(Money.NICKEL) != null){
                        count = changes.get(Money.NICKEL);
                    }
                    changes.put(Money.NICKEL, count + 1);
                    balance = balance - Money.NICKEL.getDollars();
                } else if (balance >= Money.PENNY.getDollars() && vendingMachine.getMoneys().get(Money.PENNY) > 0) {
                    int count =0 ;
                    if(changes.get(Money.PENNY) != null){
                        count = changes.get(Money.PENNY);
                    }
                    changes.put(Money.PENNY, count + 1);
                    balance = balance - Money.PENNY.getDollars();
                }

            }
        }
        return changes;
    }


}
