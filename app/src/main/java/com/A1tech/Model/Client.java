package com.A1tech.Model;

public class Client {
    int clientId;
    String email;
    int isActive;
    int isRegistered;
    double latitude;
    double longitude;
    String message;
    String password;
    String phoneNumber;
    String role;
    int status;
    String userName;

    //Constructor

    public Client() {
    }
    public Client(String password, String phoneNumber) {
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    public Client(String userName, String password, String phoneNumber) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }

    public Client(double latitude, double longitude, String phoneNumber,String message, String userName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.message = message;
        this.phoneNumber = phoneNumber;
        this.userName = userName;

    }

    public Client(int clientId, String email, int isActive, int isRegistered, double latitude, double longitude, String message, String password, String phoneNumber, int status, String userName) {
        this.clientId = clientId;
        this.email = email;
        this.isActive = isActive;
        this.isRegistered = isRegistered;
        this.latitude = latitude;
        this.longitude = longitude;
        this.message = message;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.userName = userName;

    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int isActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int isRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(int isRegistered) {
        this.isRegistered = isRegistered;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

