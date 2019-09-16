package com.creativityskills.jotech.test2.model;

public class Response<Product,List> {
    private Product product;
    private List change;

    public Response(Product product, List change) {
        this.product = product;
        this.change = change;
    }

    public Product getProduct() {
        return product;
    }

    public List getChange() {
        return change;
    }
}
