package com.creativityskills.jotech.enums;

/**
 * Items or Products supported by vending machine
 * @author oenga
 *
 *
 */
public enum Item {
    CANDY( "Candy",10), SNACK("Snack",50), NUTS("Nuts",90), COKE("Coke",25), PEPSI("Pepsi",35), SODA("Pepsi",45);
    private String name;
    private int price;

    Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
