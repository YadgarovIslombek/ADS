package com.A1tech.Model;
public class ProductType {
    int productTypeId;
    int productGroupId;
    String productGroupName;

    //Constructor

    public ProductType(int productTypeId, int productGroupId, String productGroupName) {
        this.productTypeId = productTypeId;
        this.productGroupId = productGroupId;
        this.productGroupName = productGroupName;
    }


    //getters&Setters


    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public int getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(int productGroupId) {
        this.productGroupId = productGroupId;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }
}
