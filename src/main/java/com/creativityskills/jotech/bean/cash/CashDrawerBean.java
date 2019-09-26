package com.creativityskills.jotech.bean.cash;

import com.creativityskills.jotech.bean.crud.Bean;
import com.creativityskills.jotech.model.CashDrawer;
import com.creativityskills.jotech.model.Denomination;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Local
@Stateless
public class CashDrawerBean extends Bean<CashDrawer> implements CashDrawerBeanI {
    @Inject
    private Connection connection;

    @Override
    public CashDrawer findByDenomination(Denomination denomination) {
        String sql = "SELECT * FROM cash_drawer WHERE denomination='" + denomination + "'";
        CashDrawer  cashDrawer=null;
        try {
            Statement statement = connection.createStatement();
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
        //empty object to avoid nulls
        return cashDrawer;
    }

    @Override
    public List<CashDrawer> getCashDrawerList() {
        String sql = "SELECT * FROM cash_drawer";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<CashDrawer> cashDrawers = new ArrayList<>();
         while (rs.next()) {
                CashDrawer cashDrawer = new CashDrawer();
                cashDrawer.setId(rs.getInt("id"));
                cashDrawer.setDenomination(Denomination.valueOf(rs.getString("denomination")));
                cashDrawer.setCount(rs.getLong("dn_count"));
                cashDrawers.add(cashDrawer);
            }
         return cashDrawers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
