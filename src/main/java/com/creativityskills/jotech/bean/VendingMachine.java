package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.exception.InsufficientProductQuantityException;
import com.creativityskills.jotech.exception.InsuficientAmountException;
import com.creativityskills.jotech.model.Product;
import com.creativityskills.jotech.model.Sale;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import java.math.BigDecimal;
import java.util.Date;

@Local
@Stateful
@Singleton
public class VendingMachine implements VendingMachineI {
    @EJB
    StockBeanI stockBeanI;
    @EJB
    SaleBeanI saleBeanI;

    @Override
    public BigDecimal calculateRequiredAmount(Product product, int quantity) {
        return product.getUnitPrice().multiply(new BigDecimal(quantity));
    }

    @Override
    public boolean makeSale(Product product, int quantity, BigDecimal amount) throws Exception {
        //validate amount
        if (amount.compareTo(this.calculateRequiredAmount(product, quantity)) < 0) {
            throw new InsuficientAmountException();
        }
        //validate product availability
        if (stockBeanI.getStockBalance(product) < quantity) {
            throw new InsufficientProductQuantityException();
        }
        Sale sale = new Sale();
        sale.setDate(new Date());
        sale.setAmount(this.calculateRequiredAmount(product, quantity));
        sale.setProduct(product);
        sale.setQuantity(quantity);
        return saleBeanI.makeSale(sale);
    }
}
