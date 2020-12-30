package com.A1tech.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceOrder {
    @SerializedName("name")
    String name;
    @SerializedName("mobile")
    String mobile;
    @SerializedName("address")
    String address;
    @SerializedName("user_id")
    int user_id;
    @SerializedName("orderitems")
    List<OrderItem> orderitems;

    public PlaceOrder(String name, String mobile, String address, int user_id, List<OrderItem> orderitems) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.user_id = user_id;
        this.orderitems = orderitems;
    }

    public PlaceOrder() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<OrderItem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<OrderItem> orderitems) {
        this.orderitems = orderitems;
    }
}
