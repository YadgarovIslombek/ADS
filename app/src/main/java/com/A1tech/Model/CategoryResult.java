package com.A1tech.Model;
import  com.google.gson.annotations.SerializedName;
import java.util.List;
public class CategoryResult {
    @SerializedName("productGroupList")
    List<ProductModel> productGroupList;

    //Constructor
    public CategoryResult(List<ProductModel> productIdList) {
        this.productGroupList = productIdList;
    }

    //Getters & Setters
    public List<ProductModel> getProductList() {
        return productGroupList;
    }

    public void setProductList(List<ProductModel> productIdList) {
        this.productGroupList = productIdList;
    }
}
