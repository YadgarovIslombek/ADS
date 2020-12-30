package com.A1tech.Model;
import  com.google.gson.annotations.SerializedName;
import java.util.List;
public class CategoryResult {
    @SerializedName("productList")
    List<ProductModel> productIdList;

    //Constructor
    public CategoryResult(List<ProductModel> productIdList) {
        this.productIdList = productIdList;
    }

    //Getters & Setters
    public List<ProductModel> getProductList() {
        return productIdList;
    }

    public void setProductList(List<ProductModel> productIdList) {
        this.productIdList = productIdList;
    }
}
