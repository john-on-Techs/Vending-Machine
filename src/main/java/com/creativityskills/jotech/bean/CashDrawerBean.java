package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.model.CashDrawer;
import com.creativityskills.jotech.model.Denomination;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class CashDrawerBean extends Bean<CashDrawer> implements CashDrawerBeanI{
    @Override
    public CashDrawer findByDenomination(Denomination denomination) {
        return null;
    }
}
