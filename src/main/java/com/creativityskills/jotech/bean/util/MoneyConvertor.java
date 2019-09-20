package com.creativityskills.jotech.bean.util;

import com.creativityskills.jotech.bean.cash.CashDrawerBeanI;
import com.creativityskills.jotech.model.Denomination;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Local
@Stateless
public class MoneyConvertor implements MoneyConvertorI {
    @EJB
    private CashDrawerBeanI cashDrawerBeanI;

    @Override
    public BigDecimal getMoneyValueFromDenominations(Map<Denomination, Integer> money) {
        BigDecimal amount = BigDecimal.ZERO;
        for (Map.Entry m : money.entrySet()) {
            Denomination denomination = Denomination.valueOf(m.getKey().toString());
            Integer count = (Integer) m.getValue();
            int denominationValue = denomination.getValue() * count;
            amount = amount.add(new BigDecimal(denominationValue));
        }
        return amount;

    }

    @Override
    public Map<Denomination, Integer> getDenominationsForMoney(BigDecimal amount) {
        Map<Denomination, Integer> denominations = new HashMap<>();
        for (Denomination denomination : Denomination.values()) {
            int denominationCount = getDenominationCount(denomination, amount);
            amount = amount.subtract(new BigDecimal(denomination.getValue() * denominationCount));
            denominations.put(denomination, denominationCount);
        }
        return denominations;

    }

    private int getDenominationCount(Denomination denomination, BigDecimal amount) {
        int denominationCount = 0;
        if (amount.compareTo(new BigDecimal(denomination.getValue())) >= 0) {
            double i = Double.parseDouble(amount.divide(new BigDecimal(denomination.getValue())).toString());
            i = Math.floor(i);
            denominationCount = (int) i;
            int availableDenominationCount = this.getAvailableCountForDenomination(denomination);
            return Math.min(availableDenominationCount, denominationCount);
        }
        return 0;
    }

    private int getAvailableCountForDenomination(Denomination denomination) {
        return (int) cashDrawerBeanI.findByDenomination(denomination).getCount();
    }
}
