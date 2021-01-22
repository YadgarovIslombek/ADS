package com.A1tech.Model;
public class Cart {
    int productId;
    String productName;
    int price;
    int unitOfMeasurementId;
    int measurement;
    String unitName;
    double subTotal;
    public Cart() {
    }

    public Cart(int productId, String productName, int price, String unitName,int measurement, double subTotal) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.unitName = unitName;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(int unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
    }

    public int getMeasurement() {
        return measurement;
    }

    public void setMeasurement(int measurement) {
        this.measurement = measurement;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}