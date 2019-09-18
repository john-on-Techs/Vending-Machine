package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.exception.InsufficientProductQuantityException;
import com.creativityskills.jotech.exception.InsuficientAmountException;
import com.creativityskills.jotech.model.CashDrawer;
import com.creativityskills.jotech.model.Denomination;
import com.creativityskills.jotech.model.Product;
import com.creativityskills.jotech.model.Sale;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Local
@Stateful
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

    @Override
    public BigDecimal calculateRequiredAmount(Product product, int quantity) {
        return product.getUnitPrice().multiply(new BigDecimal(quantity));
    }

    @Override
    public boolean makeSale(Product product, int quantity, Map<Denomination, Integer> denominations) throws Exception {
        //convert money denominations to value
        BigDecimal amount = moneyConvertorI.getMoneyValueromDenominations(denominations);

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
        return status;
    }

    private Map<Denomination, Integer> giveChange(BigDecimal amount) {
        return moneyConvertorI.getDenominationsForMoney(amount);
    }


}
