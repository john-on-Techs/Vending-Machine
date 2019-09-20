package com.creativityskills.jotech.bean.stock;

import com.creativityskills.jotech.bean.crud.BeanI;
import com.creativityskills.jotech.model.Product;
import com.creativityskills.jotech.model.Stock;

import javax.ejb.Local;

@Local

public interface StockBeanI extends BeanI<Stock> {
    long getStockBalance(Product product);
}
