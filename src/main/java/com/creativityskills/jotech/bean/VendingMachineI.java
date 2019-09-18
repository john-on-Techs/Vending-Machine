package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.model.Denomination;
import com.creativityskills.jotech.model.Product;

import javax.ejb.Local;
import java.math.BigDecimal;
import java.util.Map;

@Local
public interface VendingMachineI {
    BigDecimal calculateRequiredAmount(Product product, int quantity);

    boolean makeSale(Product product, int quantity, Map<Denomination,Integer> denominations) throws Exception;

}
