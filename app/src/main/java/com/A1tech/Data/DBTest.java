package com.A1tech.Data;


import com.A1tech.Model.ProductModel;
import com.A1tech.Model.ProductGroup;

import java.util.ArrayList;
import java.util.List;

public class DBTest {
    List<ProductModel> productlist = new ArrayList<>();
    List<ProductGroup> productGroupList = new ArrayList<>();

    public List<ProductModel> getProductlist() {
        ProductModel productModel = new ProductModel(1, "Olma", 5000,1,2);
        productlist.add(productModel);
        ProductModel productModel2 = new ProductModel(1, "Uzum", 15000,1,2);
        productlist.add(productModel2);
        ProductModel productModel3 = new ProductModel(1, "Anor", 52000,1,2);
        productlist.add(productModel3);
        ProductModel productModel4 = new ProductModel(1, "pomidor", 15000,1,1);
        productlist.add(productModel4);
        ProductModel productModel5 = new ProductModel(1, "Olma", 85000,1,2);
        productlist.add(productModel5);
        ProductModel productModel6 = new ProductModel(1, "bodring", 85000,1,1);
        productlist.add(productModel6);
        ProductModel productModel7 = new ProductModel(1, "Olma", 85000,1,2);
        productlist.add(productModel7);
        ProductModel productModel8 = new ProductModel(1, "sabzi", 85000,1,1);
        productlist.add(productModel8);
        ProductModel productModel9 = new ProductModel(1, "Olma", 85000,1,2);
        productlist.add(productModel9);
        ProductModel productModel10 = new ProductModel(1, "Olma", 85000,1,2);
        productlist.add(productModel10);
        ProductModel productModel11 = new ProductModel(1, "Olma", 85000,1,2);
        productlist.add(productModel11);
        ProductModel productModel12 = new ProductModel(1, "Olma", 85000,1,2);
        productlist.add(productModel12);
        ProductModel productModel13 = new ProductModel(1, "Olma", 85000,1,2);
        productlist.add(productModel13);
        ProductModel productModel14 = new ProductModel(1, "Olma", 85000,1,2);
        productlist.add(productModel14);
        return productlist;
    }
    public List<ProductGroup> getProductGroupList(){
        ProductGroup productGroup = new ProductGroup(1,1, "Sabzavotlar");
        productGroupList.add(productGroup);
        ProductGroup productGroup1 = new ProductGroup(2,1, "Mevalar");
        productGroupList.add(productGroup1);
        ProductGroup productGroup2 = new ProductGroup(3,2, "Go'sht Mahsulotlari");
        productGroupList.add(productGroup2);
        ProductGroup productGroup3 = new ProductGroup(2,2, "Ichimliklar");
        productGroupList.add(productGroup3);
        return productGroupList;
    }



}