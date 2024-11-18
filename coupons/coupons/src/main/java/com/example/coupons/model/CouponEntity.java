package com.example.coupons.model;

import java.util.List;

public class CouponEntity {
    private Long id;
    private String type;
    private double minCartValue;
    private double discountValue;
    private String applicableProductName;
    private int buyCount;
    private int getCount;
    private List<String> buyProducts;
    private List<String> getProducts;
    private String conditions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMinCartValue() {
        return minCartValue;
    }

    public void setMinCartValue(double minCartValue) {
        this.minCartValue = minCartValue;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public String getApplicableProductName() {
        return applicableProductName;
    }

    public void setApplicableProductName(String applicableProductName) {
        this.applicableProductName = applicableProductName;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public int getGetCount() {
        return getCount;
    }

    public void setGetCount(int getCount) {
        this.getCount = getCount;
    }

    public List<String> getBuyProducts() {
        return buyProducts;
    }

    public void setBuyProducts(List<String> buyProducts) {
        this.buyProducts = buyProducts;
    }

    public List<String> getGetProducts() {
        return getProducts;
    }

    public void setGetProducts(List<String> getProducts) {
        this.getProducts = getProducts;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
}
