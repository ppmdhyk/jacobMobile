package com.example.jacobmobile.model;

import com.google.gson.annotations.SerializedName;

public class Saran {

    @SerializedName("id")
    private String id;

    @SerializedName("saran")
    private String saran;

    @SerializedName("topik")
    private String topik;

    @SerializedName("urgensi")
    private String urgensi;

    @SerializedName("sentimen")
    private String sentimen;

    @SerializedName("timestamp")
    private String timestamp;

    public Saran(String id, String saran, String topik, String urgensi, String sentimen, String timestamp) {
        this.id = id;
        this.saran = saran;
        this.topik = topik;
        this.urgensi = urgensi;
        this.sentimen = sentimen;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaran() {
        return saran;
    }

    public void setSaran(String saran) {
        this.saran = saran;
    }

    public String getTopik() {
        return topik;
    }

    public void setTopik(String topik) {
        this.topik = topik;
    }

    public String getUrgensi() {
        return urgensi;
    }

    public void setUrgensi(String urgensi) {
        this.urgensi = urgensi;
    }

    public String getSentimen() {
        return sentimen;
    }

    public void setSentimen(String sentimen) {
        this.sentimen = sentimen;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
