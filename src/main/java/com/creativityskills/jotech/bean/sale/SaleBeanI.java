package com.creativityskills.jotech.bean.sale;

import com.creativityskills.jotech.model.Sale;

import javax.ejb.Local;

@Local
public interface SaleBeanI {
    boolean makeSale(Sale sale);
}
