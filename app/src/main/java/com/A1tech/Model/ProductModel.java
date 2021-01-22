package com.A1tech.Model;

public class ProductModel {
    int productId;
    String productName;
    int price;
    int measurement;
    int unitOfMeasurementId;
    String unitName;
    int productGroupId;

    //Constructor


    public ProductModel(int productGroupId) {
        this.productGroupId = productGroupId;
    }

    public ProductModel(int i, String uzum, int i1, int i2, int i3) {
    }
    //Getters & Setters
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

    public int getMeasurement() {
        return measurement;
    }

    public void setMeasurement(int measurement) {
        this.measurement = measurement;
    }

    public int getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(int unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int productGroupId() {
        return productGroupId;
    }

    public void setproductGroupId(int productGroupId) {
        this.productGroupId = productGroupId;
    }
}



