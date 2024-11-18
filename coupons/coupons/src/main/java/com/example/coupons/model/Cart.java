package com.example.coupons.model;

import java.util.List;

public class Cart {
    private List<Product> products;
    private double totalAmount;

    public Cart() {
    }

    public Cart(List<Product> products, double totalAmount) {
        this.products = products;
        this.totalAmount = totalAmount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
