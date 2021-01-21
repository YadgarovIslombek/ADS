package com.A1tech;
import com.A1tech.Model.ProductType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonResponseProductGroup {
    public ProductType[] productGroupList;
    public ProductType[] getProductTypes() {
        return productGroupList;
    }
}


