package com.creativityskills.jotech.ejb;

import com.creativityskills.jotech.exceptions.NoSuchProductExistsException;
import com.creativityskills.jotech.exceptions.NotEnoughChangeException;
import com.creativityskills.jotech.exceptions.NotEnoughMoneyException;
import com.creativityskills.jotech.model.Money;
import com.creativityskills.jotech.model.Product;

import java.util.Map;

public interface VMBuyProductRemote {
    double getAccountBalance();

    String buyProduct(Product product, Map<Money, Integer> usermoneyMap) throws NoSuchProductExistsException,
            NotEnoughMoneyException, NotEnoughChangeException;
}
