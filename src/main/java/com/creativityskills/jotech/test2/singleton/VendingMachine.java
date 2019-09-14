package com.creativityskills.jotech.test2.singleton;


import com.creativityskills.jotech.test2.exceptions.NotFullPaidException;
import com.creativityskills.jotech.test2.exceptions.NotSufficientChangeException;
import com.creativityskills.jotech.test2.model.Bag;
import com.creativityskills.jotech.test2.model.Coin;
import com.creativityskills.jotech.test2.model.Product;
import com.creativityskills.jotech.test2.model.Response;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
@Startup
@AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
public class VendingMachine {
    private static final Logger logger = Logger.getLogger(VendingMachine.class);
    private Bag<Product> productBag;
    private Bag<Coin> coinBag;
    private long totalSales;
    private Product currentPoduct;
    private long currentBalance;

    @PostConstruct
    public void setupVendingMachine() {
        productBag = new Bag<>();
        coinBag = new Bag<>();
        for (Product product : Product.values()) {
            productBag.put(product, 5);
        }
        for (Coin coin : Coin.values()) {
            coinBag.put(coin, 5);
        }
    }

    @PreDestroy
    public void reset() {
        coinBag.clear();
        productBag.clear();
        totalSales = 0;
        currentPoduct = null;
        currentBalance = 0;
    }

    public long selectProductAndGetPrice(Product product) {
        if (productBag.hasItem(product)) {
            currentPoduct = product;
            return product.getPrice();
        }
        return 0;
    }

    public Bag<Product> getProductBag() {
        return productBag;
    }

    @Lock(LockType.WRITE)
    public void insertCoin(Coin coin) {
        currentBalance = currentBalance + coin.getCoinValue();
        coinBag.add(coin);
    }

    @Lock(LockType.WRITE)
    public Response<Product, List<Coin>> getServed() {
        Product product = null;
        try {
            product = collectItem();
        } catch (NotFullPaidException e) {
            e.printStackTrace();
        }
        totalSales = totalSales + currentPoduct.getPrice();

        List<Coin> change = collectChange();

        return new Response<>(product, change);
    }

    @Lock(LockType.WRITE)
    private Product collectItem() throws NotSufficientChangeException,
            NotFullPaidException {
        if (isFullPaid()) {
            if (hasSufficientChange()) {
                productBag.deduct(currentPoduct);
                return currentPoduct;
            }
            throw new NotSufficientChangeException("Not Sufficient change in coin bag");

        }
        long remainingBalance = currentPoduct.getPrice() - currentBalance;
        throw new NotFullPaidException("Price not full paid, remaining : ",
                remainingBalance);
    }

    private List<Coin> collectChange() {
        long changeAmount = currentBalance - currentPoduct.getPrice();
        List<Coin> change = getChange(changeAmount);
        updateCoinsInBag(change);
        currentBalance = 0;
        currentPoduct = null;
        return change;
    }


    public List<Coin> refund() {
        List<Coin> refund = getChange(currentBalance);
        updateCoinsInBag(refund);
        currentBalance = 0;
        currentPoduct = null;
        return refund;
    }


    private boolean isFullPaid() {
        if (currentBalance >= currentPoduct.getPrice()) {
            return true;
        }
        return false;
    }


    private List<Coin> getChange(long amount) throws NotSufficientChangeException {
        List<Coin> changes = Collections.EMPTY_LIST;

        if (amount > 0) {
            changes = new ArrayList<Coin>();
            long balance = amount;
            while (balance > 0) {
                if (balance >= Coin.QUARTER.getCoinValue()
                        && coinBag.hasItem(Coin.QUARTER)) {
                    changes.add(Coin.QUARTER);
                    balance = balance - Coin.QUARTER.getCoinValue();

                } else if (balance >= Coin.DIME.getCoinValue()
                        && coinBag.hasItem(Coin.DIME)) {
                    changes.add(Coin.DIME);
                    balance = balance - Coin.DIME.getCoinValue();

                } else if (balance >= Coin.NICKLE.getCoinValue()
                        && coinBag.hasItem(Coin.NICKLE)) {
                    changes.add(Coin.NICKLE);
                    balance = balance - Coin.NICKLE.getCoinValue();

                } else if (balance >= Coin.PENNY.getCoinValue()
                        && coinBag.hasItem(Coin.PENNY)) {
                    changes.add(Coin.PENNY);
                    balance = balance - Coin.PENNY.getCoinValue();

                } else {
                    throw new NotSufficientChangeException("NotSufficientChange,Please try another product");
                }
            }
        }

        return changes;
    }

    public void printStats() {
        System.out.println("Total Sales : " + totalSales);
        System.out.println("Current Products in Store : " + productBag);
        System.out.println("Current Cash in Locker: " + coinBag);
    }


    private boolean hasSufficientChange() {
        return hasSufficientChangeForAmount(currentBalance - currentPoduct.getPrice());
    }

    private boolean hasSufficientChangeForAmount(long amount) {
        boolean hasChange = true;
        try {
            getChange(amount);
        } catch (NotSufficientChangeException e) {
            return hasChange = false;
        }

        return hasChange;
    }

    private void updateCoinsInBag(List<Coin> change) {
        for (Coin c : change) {
            coinBag.deduct(c);
        }
    }

    public long getTotalSales() {
        return totalSales;
    }

}
