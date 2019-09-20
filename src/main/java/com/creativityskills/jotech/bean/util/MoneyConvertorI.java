package com.creativityskills.jotech.bean.util;

import com.creativityskills.jotech.model.Denomination;

import javax.ejb.Local;
import java.math.BigDecimal;
import java.util.Map;

@Local
public interface MoneyConvertorI {
    BigDecimal getMoneyValueFromDenominations(Map<Denomination, Integer> money);

    Map<Denomination, Integer> getDenominationsForMoney(BigDecimal amount);

}
