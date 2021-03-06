package com.creativityskills.jotech.model;

public class Stock {
    private int id;
    private Product product;
    private long quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
