package com.creativityskills.jotech.bean.sale;

import com.creativityskills.jotech.model.Sale;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class SaleBean implements SaleBeanI {
    @Override
    public boolean makeSale(Sale sale) {
        return false;
    }
}
