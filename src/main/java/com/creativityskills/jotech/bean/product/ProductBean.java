package com.creativityskills.jotech.bean.product;

import com.creativityskills.jotech.bean.crud.Bean;
import com.creativityskills.jotech.model.Product;

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
public class ProductBean extends Bean<Product> implements ProductBeanI {
    @Inject
    Connection connection;
    @Override
    public List<Product> getProductsList() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM product");
            List<Product> products = new ArrayList<>();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setUnitPrice(rs.getBigDecimal("unit_price"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
