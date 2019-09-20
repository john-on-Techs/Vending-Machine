package com.creativityskills.jotech.bean.cash;

import com.creativityskills.jotech.bean.crud.Bean;
import com.creativityskills.jotech.db.DBHandler;
import com.creativityskills.jotech.model.CashDrawer;
import com.creativityskills.jotech.model.Denomination;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Local
@Stateless
public class CashDrawerBean extends Bean<CashDrawer> implements CashDrawerBeanI {
    @Inject
    private DBHandler dbHandler;

    @Override
    public CashDrawer findByDenomination(Denomination denomination) {
        String sql = "SELECT * FROM cash_drawer WHERE denomination='" + denomination + "'";
        CashDrawer cashDrawer = null;
        try {
            Statement statement = dbHandler.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                cashDrawer = new CashDrawer();
                cashDrawer.setId(rs.getInt("id"));
                cashDrawer.setDenomination(denomination);
                cashDrawer.setCount(rs.getLong("dn_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cashDrawer;
    }
}
