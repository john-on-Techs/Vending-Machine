package com.creativityskills.jotech.ejb;


import com.creativityskills.jotech.model.Money;
import com.creativityskills.jotech.model.Product;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Collection;

@Stateless
@Local
public class VMInfo implements VMInfoRemote {
    @EJB
    VendingMachine machine;

    @Override
    public Collection<Product> getProductList() {
        return machine.getProducts();

    }

    @Override
    public Collection<Money> getMoneyTypeList() {
        return machine.getMoneyType();
    }
}
