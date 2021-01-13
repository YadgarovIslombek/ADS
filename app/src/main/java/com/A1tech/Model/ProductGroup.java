package com.A1tech.Model;

public class ProductGroup {
    int id;
    int product_type_id;
    String name;
    String description;

    public ProductGroup(int id, int product_type_id, String name, String description) {
        this.id = id;
        this.product_type_id = product_type_id;
        this.name = name;
        this.description = description;
    }

    public ProductGroup(int i, String s) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_type_id() {
        return product_type_id;
    }

    public void setProduct_type_id(int product_type_id) {
        this.product_type_id = product_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
