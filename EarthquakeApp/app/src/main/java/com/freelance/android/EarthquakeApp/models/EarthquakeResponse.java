package com.freelance.android.EarthquakeApp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyaw Khine on 10/10/2017.
 */

public class EarthquakeResponse {

    //This is first step response from the earthquake.usgs.gov.

    @SerializedName("features")
    //private Features features;
    private List<Features> features = new ArrayList<Features>();

    public List<Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<Features> features) {
        this.features = features;
    }
}
