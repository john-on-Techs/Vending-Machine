package com.creativityskills.jotech.bean.cash;

import com.creativityskills.jotech.bean.crud.BeanI;
import com.creativityskills.jotech.model.CashDrawer;
import com.creativityskills.jotech.model.Denomination;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CashDrawerBeanI extends BeanI<CashDrawer> {
    CashDrawer findByDenomination(Denomination denomination);
    List<CashDrawer> getCashDrawerList();

}
