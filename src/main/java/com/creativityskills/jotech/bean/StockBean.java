package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.model.Product;

import javax.ejb.Stateless;

@Stateless
public class StockBean implements StockBeanI {
    @Override
    public long getStockBalance(Product product) {
        return 0;
    }
}
