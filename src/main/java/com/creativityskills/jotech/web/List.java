package com.creativityskills.jotech.web;

import com.creativityskills.jotech.ejb.VMBuyProductRemote;
import com.creativityskills.jotech.ejb.VMInfoRemote;
import com.creativityskills.jotech.exceptions.NoSuchProductExistsException;
import com.creativityskills.jotech.exceptions.NotEnoughChangeException;
import com.creativityskills.jotech.exceptions.NotEnoughMoneyException;
import com.creativityskills.jotech.model.Money;
import com.creativityskills.jotech.model.Product;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "list", urlPatterns = "/list")
public class List extends HttpServlet {
    @EJB
    VMInfoRemote vmInfoRemote;
    @EJB
    VMBuyProductRemote vmBuyProductRemote;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Money,Integer> userMoneyIntegerMap = new HashMap<>();
        userMoneyIntegerMap.put(Money.TWO_DOLLAR_NOTE, Integer.parseInt(request.getParameter("TWO_DOLLAR_NOTE")));
        userMoneyIntegerMap.put(Money.ONE_DOLLAR, Integer.parseInt(request.getParameter("ONE_DOLLAR")));
        userMoneyIntegerMap.put(Money.HALF_DOLLAR, Integer.parseInt(request.getParameter("HALF_DOLLAR")));
        userMoneyIntegerMap.put(Money.QUARTER, Integer.parseInt(request.getParameter("QUARTER")));
        userMoneyIntegerMap.put(Money.DIME, Integer.parseInt(request.getParameter("DIME")));
        userMoneyIntegerMap.put(Money.NICKEL, Integer.parseInt(request.getParameter("NICKEL")));
        userMoneyIntegerMap.put(Money.PENNY, Integer.parseInt(request.getParameter("PENNY")));
        Product product = Product.valueOf(request.getParameter("productChosen"));
        try {
            vmBuyProductRemote.buyProduct(product,userMoneyIntegerMap);
        } catch (NoSuchProductExistsException | NotEnoughMoneyException | NotEnoughChangeException e) {
           throw  new ServletException("Some error occurred"+e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("productTypeList", vmInfoRemote.getProductList());
        request.setAttribute("moneyTypeList", vmInfoRemote.getMoneyTypeList());
        request.getRequestDispatcher("/views/list.jsp").forward(request, response);
    }
}
