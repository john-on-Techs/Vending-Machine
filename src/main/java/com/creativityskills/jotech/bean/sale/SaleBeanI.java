package com.creativityskills.jotech.bean.sale;

import com.creativityskills.jotech.bean.crud.BeanI;
import com.creativityskills.jotech.model.Sale;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SaleBeanI extends BeanI<Sale> {
    List<Sale> getSaleList();

}
