package com.creativityskills.jotech.bean.sale;

import com.creativityskills.jotech.bean.crud.Bean;
import com.creativityskills.jotech.bean.product.ProductBeanI;
import com.creativityskills.jotech.model.Product;
import com.creativityskills.jotech.model.Sale;

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

@Local
@Stateless
public class SaleBean extends Bean<Sale> implements SaleBeanI {
    @Inject
    private Connection  connection;
    @EJB
    private ProductBeanI  productBeanI;


    @Override
    public List<Sale> getSaleList() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM sale");
            List<Sale> sales = new ArrayList<>();
            while (rs.next()){
                Sale sale = new Sale();
                sale.setId(rs.getInt("id"));
                sale.setDate(rs.getDate("date"));
                sale.setQuantity(rs.getLong("quantity"));
                sale.setAmount(rs.getBigDecimal("amount"));

                Product product = new Product();
                product.setId(rs.getInt("product"));
                sale.setProduct(productBeanI.read(product));

                sales.add(sale);
            }
            return sales;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
