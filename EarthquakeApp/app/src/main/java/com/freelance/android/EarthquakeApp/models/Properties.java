package com.freelance.android.EarthquakeApp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kyaw Khine on 10/10/2017.
 */

public class Properties {

    //This is three step response from the earthquake.usgs.gov.

    @SerializedName("mag")
    private double mag;

    @SerializedName("place")
    private String place;

    @SerializedName("time")
    private long time;

    @SerializedName("url")
    private String url;

    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

/*
  @SerializedName("updated")
    private long updated;

    @SerializedName("tz")
    private int tz;

    @SerializedName("url")
    private String url;

    @SerializedName("detail")
    private String detail;

    @SerializedName("felt")
    private int felt;

    @SerializedName("cdi")
    private double cdi;

    @SerializedName("mmi")
    private double mmi;

    @SerializedName("alert")
    private String alert;

    @SerializedName("status")
    private String status;

    @SerializedName("tsunami")
    private int tsunami;

    @SerializedName("sig")
    private int sig;

    @SerializedName("net")
    private String net;

    @SerializedName("code")
    private String code;

    @SerializedName("ids")
    private String ids;

    @SerializedName("sources")
    private String sources;

    @SerializedName("types")
    private String types;

    @SerializedName("nst")
    private String nst;

    @SerializedName("dmin")
    private double dmin;

    @SerializedName("rms")
    private double rms;

    @SerializedName("gap")
    private int gap;

    @SerializedName("magType")
    private String magType;

    @SerializedName("type")
    private String type;

    @SerializedName("title")
    private String title;


    public Properties(double mag, String place, long time, String url) {
        this.mag = mag;
        this.place = place;
        this.time = time;
        this.url = url;
    }

    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }*/
