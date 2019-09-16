package com.creativityskills.jotech.test2.model;

public enum Product {
    CANDY( 10), COKE(25),   PEPSI(35), SODA(45),SNACK(50), NUTS(90);
    private int price;

    Product(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}