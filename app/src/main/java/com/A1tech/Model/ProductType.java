package com.A1tech.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductType {

    int producTypeId;

    String typeName;

    int productGroupId;

    String productGroupName;

    String description;

    public ProductType(int producTypeId, String typeName, int productGroupId, String productGroupName, String description) {
        this.producTypeId = producTypeId;
        this.typeName = typeName;
        this.productGroupId = productGroupId;
        this.productGroupName = productGroupName;
        this.description = description;
    }

    public ProductType(int i, int i1, String sabzavotlar) {
    }

    public int getProducTypeId() {
        return producTypeId;
    }

    public void setProducTypeId(int producTypeId) {
        this.producTypeId = producTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

