package com.A1tech.Model;
public class Cart {
    int productId;
    String productName;
    String currency;
    int price;
    String unitOfMeasurement;
    int measurement;
    double subTotal;
    public Cart() {
    }

    public Cart(int productId, String productName, String currency, int price, String unitOfMeasurement, int measurement, double subTotal) {
        this.productId = productId;
        this.productName = productName;
        this.currency = currency;
        this.price = price;
        this.unitOfMeasurement = unitOfMeasurement;
        this.measurement = measurement;
        this.subTotal = subTotal;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public int getMeasurement() {
        return measurement;
    }

    public void setMeasurement(int measurement) {
        this.measurement = measurement;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}