package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.model.Product;
import com.creativityskills.jotech.model.Stock;

import javax.ejb.Local;
import javax.enterprise.inject.spi.Bean;

@Local

public interface StockBeanI extends BeanI<Stock> {
    long getStockBalance(Product product);
}
