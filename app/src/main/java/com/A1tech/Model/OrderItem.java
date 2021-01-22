package com.A1tech.Model;

public class OrderItem {
    int id;
    int orderid;
    String itemname;
    int itemMeasurment;
    String itemUnitOfMeasurment;
    double itemprice;
    double itemtotal;

    public OrderItem() {
    }

    public OrderItem(String itemname, int itemMeasurment, String itemUnitOfMeasurment, double itemprice, double itemtotal) {
        this.itemname = itemname;
        this.itemMeasurment = itemMeasurment;
        this.itemUnitOfMeasurment = itemUnitOfMeasurment;

        this.itemprice = itemprice;
        this.itemtotal = itemtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getItemMeasurment() {
        return itemMeasurment;
    }

    public void setItemMeasurment(int itemMeasurment) {
        this.itemMeasurment = itemMeasurment;
    }

    public String getItemUnitOfMeasurment() {
        return itemUnitOfMeasurment;
    }

    public void setItemUnitOfMeasurment(String itemUnitOfMeasurment) {
        this.itemUnitOfMeasurment = itemUnitOfMeasurment;
    }



    public double getItemprice() {
        return itemprice;
    }

    public void setItemprice(double itemprice) {
        this.itemprice = itemprice;
    }

    public double getItemtotal() {
        return itemtotal;
    }

    public void setItemtotal(double itemtotal) {
        this.itemtotal = itemtotal;
    }
}
