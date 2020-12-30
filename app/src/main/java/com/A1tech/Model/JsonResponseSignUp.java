package com.A1tech.Model;
public class JsonResponseSignUp {
    private String username;
    private String phone;
    private String password;

    //Constructor
    public JsonResponseSignUp(String username, String phone, String password) {
        this.username = username;
        this.phone = phone;
        this.password = password;
    }

    //Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}