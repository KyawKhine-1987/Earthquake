package com.freelance.android.EarthquakeApp.services;

import com.freelance.android.EarthquakeApp.models.EarthquakeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kyaw Khine on 10/10/2017.
 */

public interface APIService {

    @GET("fdsnws/event/1/query")
    Call<EarthquakeResponse> extractDataFromFeaturesJsonData(@Query("format") String format, @Query("eventtype") String eventtype,
                                                                  @Query("orderby") String orderby, @Query("minmag") int minmag,
                                                                  @Query("limit") int limit);
}
