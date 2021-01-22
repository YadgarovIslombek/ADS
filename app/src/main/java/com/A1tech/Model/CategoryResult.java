package com.A1tech.Model;
import  com.google.gson.annotations.SerializedName;
import java.util.List;
public class CategoryResult {
    @SerializedName("productList")
    List<ProductModel> productList;

    //Constructor
    public CategoryResult(List<ProductModel> productList) {
        this.productList = productList;
    }

    //Getters & Setters
    public List<ProductModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductModel> productList) {
        this.productList = productList;
    }
}
