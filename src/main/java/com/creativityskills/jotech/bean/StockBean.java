package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.db.DBHandler;
import com.creativityskills.jotech.model.Product;
import com.creativityskills.jotech.model.Stock;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Stateless
@Local
public class StockBean extends Bean<Stock> implements StockBeanI {

    @Inject
    private DBHandler dbHandler;
    @EJB
    private ProductBeanI productBeanI;

    @Override
    public long getStockBalance(Product product) {
        String sql = "SELECT * FROM stock WHERE product=" + product.getId();
        try {
            Statement statement = dbHandler.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getLong("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;


    }
}
