package com.creativityskills.jotech.bean.stock;

import com.creativityskills.jotech.bean.crud.Bean;
import com.creativityskills.jotech.bean.product.ProductBeanI;
import com.creativityskills.jotech.model.Product;
import com.creativityskills.jotech.model.Stock;

import javax.ejb.EJB;
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

@Stateless
@Local
public class StockBean extends Bean<Stock> implements StockBeanI {

    @Inject
    private Connection connection;
    @Inject
    ProductBeanI productBeanI;

    @Override
    public long getStockBalance(Product product) {
        return getStockForProduct(product).getQuantity();
    }
    @Override
    public Stock getStockForProduct(Product product) {
        String sql = "SELECT * FROM stock WHERE product=" + product.getId();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                Stock stock = new Stock();
                stock.setId(rs.getInt("id"));
                stock.setProduct(product);
                stock.setQuantity(rs.getLong("quantity"));
                return stock;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Stock();

    }

    @Override
    public List<Stock> getStockList() {
        String sql = "SELECT * FROM stock";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Stock> stockList = new ArrayList<>();
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setId(rs.getInt("id"));

                Product product = new Product();
                product.setId(rs.getInt("product"));
                stock.setProduct(productBeanI.read(product));
                stock.setQuantity(rs.getLong("quantity"));
                stockList.add(stock);
            }
            return stockList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();

    }


}
