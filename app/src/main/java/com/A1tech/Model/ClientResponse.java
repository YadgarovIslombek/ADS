package com.A1tech.Model;
public class ClientResponse {
    private int status;
    private String message;
    private User user;
    public ClientResponse(int status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
