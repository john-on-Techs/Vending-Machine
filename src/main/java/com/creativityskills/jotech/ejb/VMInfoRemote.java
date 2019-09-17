package com.creativityskills.jotech.ejb;

import com.creativityskills.jotech.model.Money;
import com.creativityskills.jotech.model.Product;

import java.util.Collection;

public interface VMInfoRemote {
    Collection<Product> getProductList();

    Collection<Money> getMoneyTypeList();
}
