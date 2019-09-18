package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.model.Product;

import javax.ejb.Local;
import java.math.BigDecimal;

@Local
public interface VendingMachineI {
    BigDecimal calculateRequiredAmount(Product product,int quantity);
    boolean makeSale(Product product,int quantity, BigDecimal amount) throws Exception;

}
