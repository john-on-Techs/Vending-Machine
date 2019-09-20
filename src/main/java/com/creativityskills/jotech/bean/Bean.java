package com.creativityskills.jotech.bean;

import com.creativityskills.jotech.db.DBHandler;
import com.creativityskills.jotech.model.CashDrawer;
import com.creativityskills.jotech.model.Product;
import com.creativityskills.jotech.model.Sale;
import com.creativityskills.jotech.model.Stock;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Local
@Stateless
public class Bean<T> implements BeanI<T> {
    @Inject
    DBHandler dbHandler;

    @Override
    public T create(T t) {
        String sql = null;
        Statement stmt = null;
        if (t instanceof CashDrawer) {
            CashDrawer cashDrawer = (CashDrawer) t;
            sql = "INSERT INTO cash_drawer(denomination,dn_count)VALUES('" + cashDrawer.getDenomination() + "'," + cashDrawer.getCount() + ");";


        } else if (t instanceof Product) {
            Product product = (Product) t;
            sql = "INSERT INTO product(name,unit_price)VALUES('" + product.getName() + "'," + product.getUnitPrice() + ");";

        } else if (t instanceof Sale) {
            Sale sale = (Sale) t;
            sql = "INSERT INTO sale(date,product,quantity,amount)VALUES('" + sale.getDate() + "'," + sale.getProduct().getId() + "," + sale.getQuantity() + "," + sale.getAmount() + "  );";

        } else if (t instanceof Stock) {
            Stock stock = (Stock) t;
            sql = "INSERT INTO stock(product,quantity)VALUES(" + stock.getProduct().getId() + "," + stock.getQuantity() + ")";

        }
        try {
            stmt = dbHandler.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public T read(T t) {
        String sql = null;

        if (t instanceof CashDrawer) {
            CashDrawer cashDrawer = (CashDrawer) t;
            sql = "SELECT * FROM cash_drawer WHERE id=" + cashDrawer.getId();


        } else if (t instanceof Product) {
            Product product = (Product) t;
            sql = "SELECT * FROM product WHERE id=" + product.getId();
        } else if (t instanceof Sale) {
            Sale sale = (Sale) t;
            sql = "SELECT * FROM sale WHERE id=" + sale.getId();
        } else if (t instanceof Stock) {
            Stock stock = (Stock) t;
            sql = "SELECT * FROM stock where id=" + stock.getId();
        }
        try {
            Statement stmt = dbHandler.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return t;
    }

    @Override
    public T update(T t) {
        Statement stmt = null;
        String sql = null;

        if (t instanceof CashDrawer) {
            CashDrawer cashDrawer = (CashDrawer) t;
            sql = "UPDATE  cash_drawer SET denomination='" + cashDrawer.getDenomination() + "',count=" + cashDrawer.getCount() + ");";

        } else if (t instanceof Product) {
            Product product = (Product) t;
            sql = "UPDATE  product SET name='" + product.getName() + "',unit_price=" + product.getUnitPrice() + ");";

        } else if (t instanceof Sale) {
            Sale sale = (Sale) t;
            sql = "UPDATE sale SET  date='" + sale.getDate() + "',product=" + sale.getProduct().getId() + ",quantity=" + sale.getQuantity() + ",amount=" + sale.getAmount() + "  );";

        } else if (t instanceof Stock) {
            Stock stock = (Stock) t;
            sql = "UPDATE  stock SET product=" + stock.getProduct().getId() + ",quantity=" + stock.getQuantity() + ")";

        }
        try {
            stmt = dbHandler.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public boolean delete(T t) {
        String sql = null;
        Statement stmt = null;
        if (t instanceof CashDrawer) {
            CashDrawer cashDrawer = (CashDrawer) t;
            sql = "DELETE FROM cash_drawer WHERE id=" + cashDrawer.getId();

        } else if (t instanceof Product) {
            Product product = (Product) t;
            sql = "DELETE  FROM product WHERE id=" + product.getId();

        } else if (t instanceof Sale) {
            Sale sale = (Sale) t;
            sql = "DELETE  FROM sale WHERE id=" + sale.getId();

        } else if (t instanceof Stock) {
            Stock stock = (Stock) t;
            sql = "DELETE  FROM stock where id=" + stock.getId();

        }
        try {
            stmt = dbHandler.getConnection().createStatement();
            return stmt.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                