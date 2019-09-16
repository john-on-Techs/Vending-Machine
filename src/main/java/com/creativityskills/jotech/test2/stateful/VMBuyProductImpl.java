package com.creativityskills.jotech.test2.stateful;

import com.creativityskills.jotech.test2.model.Coin;
import com.creativityskills.jotech.test2.model.Product;
import com.creativityskills.jotech.test2.model.Response;
import com.creativityskills.jotech.test2.singleton.VendingMachine;
import org.jboss.logging.Logger;

import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateful
@AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
@Remote(VMBuyProductImpl.class)
public class VMBuyProductImpl implements VendingMachineRemote {
    private static final Logger logger = Logger.getLogger(VendingMachine.class);

    @Inject
    private VendingMachine vendingMachine;
    private int money;


    @Override
    public String buyProduct() {
       Product product = Product.CANDY;
        final long productPrice = vendingMachine.selectProductAndGetPrice(product);
        vendingMachine.insertCoin(Coin.DIME);
        //check if price is returned else no product exists
        Response<Product, List<Coin>> response = vendingMachine.getServed();
        Product boughtProduct = response.getProduct();
        List<Coin> change = response.getChange();
        logger.infov("Product {0} bought.", product);
        return "Thank you for buying: " + product;
    }
}
