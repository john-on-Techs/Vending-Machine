package com.creativityskills.jotech.ejb;

import com.creativityskills.jotech.exceptions.NoSuchProductExistsException;
import com.creativityskills.jotech.exceptions.NotEnoughChangeException;
import com.creativityskills.jotech.exceptions.NotEnoughMoneyException;
import com.creativityskills.jotech.model.Money;
import com.creativityskills.jotech.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Stateful

public class VMBuyProduct implements VMBuyProductRemote {
    @EJB
    VendingMachine machine;
    @Inject
    CalculatorI calculateChangeI;
    private double machineMoney;

    @PostConstruct
    public void createCustomer() {
        final Map<Money, Integer> money = machine.getMoneys();
        int twoDollarNote = money.get(Money.TWO_DOLLAR_NOTE);
        int oneDollarNote = money.get(Money.ONE_DOLLAR);
        int halfDollar = money.get(Money.HALF_DOLLAR);
        int quarters = money.get(Money.QUARTER);
        int dimes = money.get(Money.DIME);
        int nickels = money.get(Money.NICKEL);
        int pennies = money.get(Money.PENNY);
        machineMoney = calculateChangeI.calculate(
                twoDollarNote,
                oneDollarNote,
                halfDollar,
                quarters,
                dimes,
                nickels,
                pennies
        );
    }

    @Override
    public double getAccountBalance() {
        return machineMoney;
    }

    @Override
    public String buyProduct(Product product, Map<Money, Integer> usermoneyMap) throws NoSuchProductExistsException,
            NotEnoughMoneyException, NotEnoughChangeException {
        final double productPrice = machine.getProductPrice(product);
        double userMoney;
        int twoDollarNote = usermoneyMap.get(Money.TWO_DOLLAR_NOTE);
        int oneDollarNote = usermoneyMap.get(Money.ONE_DOLLAR);
        int halfDollar = usermoneyMap.get(Money.HALF_DOLLAR);
        int quarters = usermoneyMap.get(Money.QUARTER);
        int dimes = usermoneyMap.get(Money.DIME);
        int nickels = usermoneyMap.get(Money.NICKEL);
        int pennies = usermoneyMap.get(Money.PENNY);
        userMoney = calculateChangeI.calculate(
                twoDollarNote,
                oneDollarNote,
                halfDollar,
                quarters,
                dimes,
                nickels,
                pennies
        );
        double balance = calculateChangeI.calculateBalance(productPrice, userMoney);
        if (userMoney >= productPrice) {
            if (calculateChangeI.canMachineGiveBalance((machineMoney + userMoney), balance)) {
                //update machine money
                machine.insertMoney(Money.TWO_DOLLAR_NOTE, twoDollarNote);
                machine.insertMoney(Money.ONE_DOLLAR, oneDollarNote);
                machine.insertMoney(Money.HALF_DOLLAR, halfDollar);
                machine.insertMoney(Money.QUARTER, quarters);
                machine.insertMoney(Money.DIME, dimes);
                machine.insertMoney(Money.NICKEL, nickels);
                machine.insertMoney(Money.PENNY, pennies);

                //deduct balance from machine
                Map<Money, Integer> change = calculateChangeI.calculateNumberOfEachTypeToReturn(balance);

                int twoDollarNoteChange = 0;
                if(change.get(Money.TWO_DOLLAR_NOTE)!= null) {
                    twoDollarNoteChange=change.get(Money.TWO_DOLLAR_NOTE);
                }
                int oneDollarNoteChange = 0;
                if(change.get(Money.ONE_DOLLAR)!= null) {
                    oneDollarNote=change.get(Money.ONE_DOLLAR);
                }
                int halfDollarChange = 0;
                if(change.get(Money.HALF_DOLLAR)!= null) {
                    halfDollarChange=change.get(Money.HALF_DOLLAR);
                }

                int quartersChange = 0;
                if(change.get(Money.QUARTER)!= null) {
                    quartersChange=change.get(Money.QUARTER);
                }
                int dimesChange =0;
                if( change.get(Money.DIME)!= null){
                    dimesChange=  change.get(Money.DIME);
                }
                int nickelsChange = 0;
                if(change.get(Money.NICKEL)!= null){
                    change.get(Money.NICKEL);
                }
                int penniesChange = 0;
                if(change.get(Money.PENNY)!= null){
                    penniesChange = change.get(Money.PENNY);
                }
                //deduct money from machine
                machine.deductMoney(Money.TWO_DOLLAR_NOTE, twoDollarNoteChange);
                machine.deductMoney(Money.ONE_DOLLAR, oneDollarNoteChange);
                machine.deductMoney(Money.HALF_DOLLAR, halfDollarChange);
                machine.deductMoney(Money.QUARTER, quartersChange);
                machine.deductMoney(Money.DIME, dimesChange);
                machine.deductMoney(Money.NICKEL, nickelsChange);
                machine.deductMoney(Money.PENNY, penniesChange);
                //add to sales
                machine.makeSale(product);

            } else {
                throw new NotEnoughChangeException();
            }
        }
        return "Product " + product + "purchased";


    }
}
