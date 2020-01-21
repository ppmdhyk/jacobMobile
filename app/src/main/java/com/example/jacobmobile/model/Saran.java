package com.example.jacobmobile.model;

import com.google.gson.annotations.SerializedName;

public class Saran {

    @SerializedName("saran")
    private String saran;

    public Saran(){}

    public Saran(String saran) {
        this.saran = saran;
    }

    public String getSaran() {
        return saran;
    }

    public void setSaran(String saran) {
        this.saran = saran;
    }
}
