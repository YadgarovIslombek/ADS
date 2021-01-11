package com.A1tech.Model;

import com.google.gson.annotations.SerializedName;

public class ClientResponse {
    @SerializedName("status")
    int status;
    @SerializedName("message")
    String message;
    @SerializedName("client")
    Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(int code) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
