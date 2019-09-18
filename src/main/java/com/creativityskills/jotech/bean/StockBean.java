package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.model.Product;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class StockBean implements StockBeanI {
    @Override
    public long getStockBalance(Product product) {
        return 0;
    }
}
