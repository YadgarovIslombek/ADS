package com.A1tech.Model;
public class ProductType {
    int productGroupId;
    String productGroupName;
    Integer productTypeId;
    String productTypeName;

    public ProductType(int productGroupId, String productGroupName, Integer productTypeId, String productTypeName) {
        this.productGroupId = productGroupId;
        this.productGroupName = productGroupName;
        this.productTypeId = productTypeId;
        this.productTypeName = productTypeName;
    }

    public ProductType(int i, int i1, String sabzavotlar) {
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

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
}
