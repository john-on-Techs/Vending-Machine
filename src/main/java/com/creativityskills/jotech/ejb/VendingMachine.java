package com.creativityskills.jotech.ejb;

import com.creativityskills.jotech.exceptions.NoSuchProductExistsException;
import com.creativityskills.jotech.model.Money;
import com.creativityskills.jotech.model.Product;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Singleton
@Startup

public class VendingMachine {
    private Logger logger = Logger.getLogger(VendingMachine.class);
    private Map<Money, Integer> moneys;
    private Map<Product, Integer> products;
    private List<Product> sales;


    @PostConstruct
    public void setupVendingMachine() {
        moneys = new HashMap<>();
        products = new HashMap<>();
        sales = new ArrayList<>();
        for (Product product : Product.values()) {
            products.put(product, 5);
        }
        for (Money money : Money.values()) {
            moneys.put(money, 5);
        }
    }

    public double getProductPrice(Product product) throws NoSuchProductExistsException {

        return selectProduct(product).getPrice();
    }

    public Product selectProduct(Product product) throws NoSuchProductExistsException {
        if (!products.containsKey(product)) {
            throw new NoSuchProductExistsException("Product " + product + " does not exist");
        }
        return product;
    }

    public void insertMoney(Money money, int quantity) {
        int count = moneys.get(money);
        moneys.put(money, count + quantity);
    }

    public void deductMoney(Money money, int quantity) {
        int count = moneys.get(money);
        moneys.put(money, count - quantity);
    }


    @Lock(LockType.READ)
    public Collection<Product> getProducts() {
        return Collections.unmodifiableCollection(products.keySet());
    }

    @Lock(LockType.READ)
    public Collection<Money> getMoneyType() {
        return Collections.unmodifiableCollection(moneys.keySet());
    }

    @Lock(LockType.READ)
    public Map<Money, Integer> getMoneys() {
        return moneys;
    }

    public void makeSale(Product product) {
        sales.add(product);
    }


}
