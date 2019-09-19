package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.exception.InsufficientProductQuantityException;
import com.creativityskills.jotech.exception.InsuficientAmountException;
import com.creativityskills.jotech.model.CashDrawer;
import com.creativityskills.jotech.model.Denomination;
import com.creativityskills.jotech.model.Product;
import com.creativityskills.jotech.model.Sale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Local
@Singleton
public class VendingMachine implements VendingMachineI {
    @EJB
    StockBeanI stockBeanI;
    @EJB
    SaleBeanI saleBeanI;
    @EJB
    private MoneyConvertorI moneyConvertorI;
    @EJB
    CashDrawerBeanI cashDrawerBeanI;

    @PostConstruct
    public void initialize() {
        // check if money is there or initialize
        for (Denomination denomination : Denomination.values()) {
            CashDrawer cashDrawer = cashDrawerBeanI.findByDenomination(denomination);
            if (cashDrawer == null) {
                cashDrawer = new CashDrawer();
                cashDrawer.setCount(10);
                cashDrawer.setDenomination(denomination);
                cashDrawerBeanI.update(cashDrawer);
            }
        }
    }

    @Override
    public BigDecimal calculateRequiredAmount(Product product, int quantity) {
        return product.getUnitPrice().multiply(new BigDecimal(quantity));
    }

    @Override
    public boolean makeSale(Product product, int quantity, Map<Denomination, Integer> denominations) throws Exception {
        //convert money denominations to value
        BigDecimal amount = moneyConvertorI.getMoneyValueFromDenominations(denominations);

        //check if the  amount supplied is enough to purchase the quantity
        if (amount.compareTo(this.calculateRequiredAmount(product, quantity)) < 0) {
            throw new InsuficientAmountException();
        }
        //check if the quantity required is available in stock
        if (stockBeanI.getStockBalance(product) < quantity) {
            throw new InsufficientProductQuantityException();
        }

        //add denominations supplied to the VM cash drawer
        for (Map.Entry m : denominations.entrySet()) {
            Denomination denomination = Denomination.valueOf(m.getKey().toString());
            CashDrawer cashDrawer = cashDrawerBeanI.findByDenomination(denomination);
            cashDrawer.setCount(cashDrawer.getCount() + (Integer) m.getValue());
            //update the denomination in the VM's cashdrawer
            cashDrawerBeanI.update(cashDrawer);
        }
        //create sale object representation
        Sale sale = new Sale();
        sale.setDate(new Date());
        sale.setAmount(this.calculateRequiredAmount(product, quantity));
        sale.setProduct(product);
        sale.setQuantity(quantity);
        //persist sale to db
        boolean status = saleBeanI.makeSale(sale);
        //check if the customer is to be given change
        if (amount.compareTo(this.calculateRequiredAmount(product, quantity)) > 0) {
            status = status && !giveChange(amount.subtract(this.calculateRequiredAmount(product, quantity))).isEmpty();
        }
        if (!status) {
            this.refundCustomerMoney(denominations);
        }
        return status;
    }

    private void refundCustomerMoney(Map<Denomination, Integer> denominations) {        //dispense
        System.out.println(denominations);
    }

    private Map<Denomination, Integer> giveChange(BigDecimal amount) {
        Map<Denomination, Integer> map = moneyConvertorI.getDenominationsForMoney(amount);
        return moneyConvertorI.getMoneyValueFromDenominations(map).equals(amount) ? map : new HashMap<Denomination, Integer>();
    }


}
