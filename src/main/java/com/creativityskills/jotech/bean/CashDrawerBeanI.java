package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.model.CashDrawer;
import com.creativityskills.jotech.model.Denomination;

import javax.ejb.Local;

@Local
public interface CashDrawerBeanI extends BeanI<CashDrawer> {
    CashDrawer findByDenomination(Denomination denomination);
}
