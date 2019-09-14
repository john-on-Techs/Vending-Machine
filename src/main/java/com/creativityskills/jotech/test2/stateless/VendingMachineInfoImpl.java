package com.creativityskills.jotech.test2.stateless;

import com.creativityskills.jotech.test2.singleton.VendingMachine;
import com.creativityskills.jotech.test2.model.Product;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Map;

@Stateless
@Remote(VendingMachineInfoImpl.class)
public class VendingMachineInfoImpl implements VendingMachineInfo {
    @Inject
    VendingMachine vendingMachine;

    @Override
    public String display() {
        final Map<Product,Integer> productMap = vendingMachine.getProductBag().getBag();
        final StringBuilder builder = new StringBuilder();
        for (Product product :productMap.keySet()){
            builder.append(product);
            builder.append(System.lineSeparator());
        }
        return builder.toString();

    }
}
