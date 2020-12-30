package com.A1tech.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class User {
    int clientId;
    String email;
    int isActive;
    int isRegistered;
    double latitude;
    double longitude;
   String password;
   String phoneNumber;
     int status;
     String userName;
   String qosh;
  String address;
    //Constructor

    public User() {
    }
    public User(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    public User(String userName, String password, String phoneNumber) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }

    public User(double latitude, double longitude, String phoneNumber, String userName, String qosh, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.qosh = qosh;
        this.address = address;
    }

    public User(int clientId, String email, int isActive, int isRegistered, double latitude, double longitude, String password, String phoneNumber, int status, String userName, String qosh, String address) {
        this.clientId = clientId;
        this.email = email;
        this.isActive = isActive;
        this.isRegistered = isRegistered;
        this.latitude = latitude;
        this.longitude = longitude;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.userName = userName;
        this.qosh = qosh;
        this.address = address;
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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsRegistered() {
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

    public String getQosh() {
        return qosh;
    }

    public void setQosh(String qosh) {
        this.qosh = qosh;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

