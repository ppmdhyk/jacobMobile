package com.example.jacobmobile.model;

import com.google.gson.annotations.SerializedName;

public class PostSaran {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    Saran mSaran;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Saran getSaran() {
        return mSaran;
    }
    public void setKontak(Saran saran) {
        mSaran = saran;
    }
}
