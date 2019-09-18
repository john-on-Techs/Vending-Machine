package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.model.Product;

import javax.ejb.Local;

@Local

public interface StockBeanI {
    long getStockBalance(Product product);
}
