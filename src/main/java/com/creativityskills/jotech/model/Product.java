package com.creativityskills.jotech.model;

public enum Product {
    CANDY(0.10), SNACK(0.50), NUTS(0.90), COKE(0.25), PEPSI(0.35), SODA(0.45);
    private double price;

    Product(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
