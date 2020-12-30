package com.A1tech.Model;
public class Order {
    int id;
    int user_id;
    int order_id;
    int date;
    String item_name;
    int item_measurment;
    String item_unitOfMeasurment;
    String currency;
    double item_price;                 // productni puli
    double item_total;                 //bir productni qancha olganligi
    double subTotal;                   //bu orderni jami summasi
    int status;                        // bu status ishlanmoqda, yo'lda , dukonda, yetkazilmoqda, yetkazilgan shu statusla bo'lishi mumkin.

    public Order() {
    }


    public Order(int id, int order_id, int date, String currency, double subTotal, int status) {
        this.id = id;
        this.order_id = order_id;
        this.date = date;
        this.currency = currency;
        this.subTotal = subTotal;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_measurment() {
        return item_measurment;
    }

    public void setItem_measurment(int item_measurment) {
        this.item_measurment = item_measurment;
    }

    public String getItem_unitOfMeasurment() {
        return item_unitOfMeasurment;
    }

    public void setItem_unitOfMeasurment(String item_unitOfMeasurment) {
        this.item_unitOfMeasurment = item_unitOfMeasurment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public double getItem_total() {
        return item_total;
    }

    public void setItem_total(double item_total) {
        this.item_total = item_total;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}