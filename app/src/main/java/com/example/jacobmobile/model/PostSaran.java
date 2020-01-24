package com.example.jacobmobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostSaran {

    @SerializedName("status")
    private String status;

    @SerializedName("result")
    private List<Saran> mSaran;

    @SerializedName("message")
    private String message;

    public PostSaran(String status, List<Saran> mSaran, String message) {
        this.status = status;
        this.mSaran = mSaran;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Saran> getmSaran() {
        return mSaran;
    }

    public void setmSaran(List<Saran> mSaran) {
        this.mSaran = mSaran;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
