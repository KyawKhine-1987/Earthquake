package com.freelance.android.EarthquakeApp.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kyaw Khine on 10/10/2017.
 */

public class RetrofitClient {

    //public static final String Base_url = "http://earthquake.usgs.gov/";

    //public static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {

        //if (retrofit == null) {
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        //}

        return retrofit;
    }
}
