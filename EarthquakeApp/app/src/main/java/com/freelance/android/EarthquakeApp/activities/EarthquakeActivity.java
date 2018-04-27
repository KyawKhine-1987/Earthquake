package com.freelance.android.EarthquakeApp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.freelance.android.EarthquakeApp.R;
import com.freelance.android.EarthquakeApp.adapter.EarthquakeAdapter;
import com.freelance.android.EarthquakeApp.models.EarthquakeResponse;
import com.freelance.android.EarthquakeApp.models.Features;
import com.freelance.android.EarthquakeApp.models.Properties;
import com.freelance.android.EarthquakeApp.services.APIService;
import com.freelance.android.EarthquakeApp.services.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EarthquakeActivity extends AppCompatActivity implements EarthquakeAdapter.ListItemClickListener {

    private static final String LOG_TAG = EarthquakeActivity.class.getName();

    List<Features> featuresList;
    Properties properties;
    public static final String Base_url = "http://earthquake.usgs.gov/";
    APIService service;

    private RecyclerView recyclerView;
    private EarthquakeAdapter adapter;
    TextView mEmptyStateTextView;

    //private static final int EARTHQUAKE_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: onCreate() called...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        initViews();
    }

    private void initViews() {
        Log.i(LOG_TAG, "TEST: initViews() called...");

        recyclerView = (RecyclerView) this.findViewById(R.id.rvEarthquakeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        loadJSON();
    }

    private void loadJSON() {
        Log.i(LOG_TAG, "TEST: loadJSON() called...");

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        final String minMagnitude = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));

        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));


        service = RetrofitClient.getClient(Base_url).create(APIService.class);

        service.extractDataFromFeaturesJsonData(
                getString(R.string.format),
                getString(R.string.eventtype),
                orderBy,
                Integer.parseInt(minMagnitude),
                10)//Integer.parseInt(R.string.limit + "")
                .enqueue(new Callback<EarthquakeResponse>() {
                    @Override
                    public void onResponse(Call<EarthquakeResponse> call, Response<EarthquakeResponse> response) {
                        Log.i(LOG_TAG, "TEST: onResponse() called...");

                        //Toast.makeText(EarthquakeActivity.this, "API Call is successful.", Toast.LENGTH_SHORT).show();

                        try {
                            if (response.isSuccessful()) {

                                featuresList = response.body().getFeatures();
                                properties = featuresList.get(0).getProperties();

                                adapter = new EarthquakeAdapter(getApplicationContext(), featuresList, EarthquakeActivity.this);
                                recyclerView.setAdapter(adapter);

                                progressBarLoading();
                            }
                        } catch (IndexOutOfBoundsException e) {
                            Log.e(LOG_TAG, e.toString());

                            Toast.makeText(EarthquakeActivity.this, "Invalid Minimum Magnitude \nwhich isn't accepted 8 or 9.", Toast.LENGTH_SHORT).show();
                            progressBarLoading();
                        }
                        /*catch (NullPointerException e) {
                            //Log.d("Error", e.getMessage());
                            Log.e(LOG_TAG, e.toString());

                            Toast.makeText(EarthquakeActivity.this, "Invalid Minimum Magnitude or \nwhich isn't accepted 8 or 9.", Toast.LENGTH_SHORT).show();
                            progressBarLoading();
                        } */
                    }

                    @Override
                    public void onFailure(Call<EarthquakeResponse> call, Throwable t) {
                        Log.i(LOG_TAG, "TEST: onFailure() called...");

                        //Log.e("Error", t.getMessage());
                        //Toast.makeText(EarthquakeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        loader();
                    }
                });
    }

    private void loader() {
        Log.i(LOG_TAG, "TEST: loader() called...");

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        mEmptyStateTextView = (TextView) findViewById(R.id.tvEmpty);

        if (networkInfo != null && networkInfo.isConnected()) {

            mEmptyStateTextView.setText(R.string.empty_textview);
            progressBarLoading();
        } else {

            mEmptyStateTextView.setText(R.string.noIC_textview);
            progressBarLoading();
        }
    }

    @Override
    public void onListItemClick(int position) {
        Log.i(LOG_TAG, "TEST : onListItemClick() called...");

        String url = featuresList.get(position).getProperties().getUrl();

        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(LOG_TAG, "TEST : onCreateOptionsMenu() called...");

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(LOG_TAG, "TEST : onOptionsItemSelected() called...");

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void progressBarLoading() {
        Log.i(LOG_TAG, "TEST : loading() called...");

        View loadingIndicator = findViewById(R.id.pbLoadingIndicator);
        loadingIndicator.setVisibility(View.INVISIBLE);
    }
}
