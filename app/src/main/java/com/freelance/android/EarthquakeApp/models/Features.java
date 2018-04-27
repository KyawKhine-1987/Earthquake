package com.freelance.android.EarthquakeApp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kyaw Khine on 10/10/2017.
 */

public class Features {

    //This is second step response from the earthquake.usgs.gov.

    @SerializedName("properties")
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
