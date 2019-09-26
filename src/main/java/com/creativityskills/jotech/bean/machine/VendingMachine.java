package com.creativityskills.jotech.bean.machine;

import com.creativityskills.jotech.bean.cash.CashDrawerBeanI;
import com.creativityskills.jotech.bean.convertor.MoneyConvertorI;
import com.creativityskills.jotech.bean.product.ProductBeanI;
import com.creativityskills.jotech.bean.sale.SaleBeanI;
import com.creativityskills.jotech.bean.stock.StockBeanI;
import com.creativityskills.jotech.exception.InsufficientProductQuantityException;
import com.creativityskills.jotech.exception.InsuficientAmountException;
import com.creativityskills.jotech.model.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Local
@Singleton
@Startup
public class VendingMachine implements VendingMachineI {
    @EJB
    StockBeanI stockBeanI;
    @EJB
    SaleBeanI saleBeanI;
    @EJB
    private ProductBeanI productBeanI;
    @EJB
    CashDrawerBeanI cashDrawerBeanI;
    @EJB
    private MoneyConvertorI moneyConvertorI;

    @PostConstruct
    public void initialize() {
        // check if money is there or initialize

        for (Denomination denomination : Denomination.values()) {
            CashDrawer cashDrawer = cashDrawerBeanI.findByDenomination(denomination);
            if (cashDrawer ==null) {
                cashDrawer = new CashDrawer();
                cashDrawer.setCount(10);
                cashDrawer.setDenomination(denomination);
                cashDrawerBeanI.create(cashDrawer);

            }
        }
    }

    @Override
    public BigDecimal calculateRequiredAmount(Product product, int quantity) {
        return product.getUnitPrice().multiply(new BigDecimal(quantity));
    }

    @Override
    public boolean makeSale(Product product, int quantity, Map<Denomination, Integer> denominations) throws Exception {
        //add denominations supplied to the VM cash drawer
        for (Map.Entry m : denominations.entrySet()) {
            Denomination denomination = Denomination.valueOf(m.getKey().toString());
            CashDrawer cashDrawer = cashDrawerBeanI.findByDenomination(denomination);
            cashDrawer.setCount(cashDrawer.getCount() + (Integer) m.getValue());
            //update the denomination in the VM's cashdrawer
            cashDrawerBeanI.update(cashDrawer);
        }
        //convert money denominations to value
        BigDecimal userAmount = moneyConvertorI.getMoneyValueFromDenominations(denominations);
        BigDecimal requiredAmount = this.calculateRequiredAmount(product, quantity);
        BigDecimal balance = userAmount.subtract(requiredAmount);

        //check if the  amount supplied is enough to purchase the quantity


        if (userAmount.compareTo(requiredAmount) < 0) {
            dispenseMoney(denominations);
            throw new InsuficientAmountException();
        }
        //check if the quantity required is available in stock
        if (stockBeanI.getStockBalance(product) < quantity) {
            dispenseMoney(denominations);
            throw new InsufficientProductQuantityException();
        }


        //create sale object representation
        Sale sale = new Sale();
        sale.setDate(new Date());
        sale.setAmount(requiredAmount);
        sale.setProduct(product);
        sale.setQuantity(quantity);

        //if we are to give change and we can give change
        if (userAmount.compareTo(requiredAmount) > 0 && !giveChange(balance).isEmpty()) {
            //then we give change and continue

            dispenseMoney(giveChange(balance));
        }
        // record the sale transaction to db
        saleBeanI.create(sale);
        //sale made successfully
        //update stock of the product by reducing the quantity
        Stock stock = stockBeanI.getStockForProduct(product);
        stock.setQuantity(stock.getQuantity() - quantity);
        stockBeanI.update(stock);
        return true;
    }

    private void dispenseMoney(Map<Denomination, Integer> denominations) {
        for (Map.Entry m : denominations.entrySet()) {
            Denomination denomination = Denomination.valueOf(m.getKey().toString());
            CashDrawer cashDrawer = cashDrawerBeanI.findByDenomination(denomination);
            cashDrawer.setCount(cashDrawer.getCount() - (Integer) m.getValue());
            //update the denomination in the VM's cashdrawer
            cashDrawerBeanI.update(cashDrawer);
        }
    }

    private Map<Denomination, Integer> giveChange(BigDecimal amount) {
        Map<Denomination, Integer> map = moneyConvertorI.getDenominationsForMoney(amount);
        return moneyConvertorI.getMoneyValueFromDenominations(map).equals(amount) ? map : new HashMap<Denomination, Integer>();
    }


}
