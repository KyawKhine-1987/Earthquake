package com.freelance.android.EarthquakeApp.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freelance.android.EarthquakeApp.R;
import com.freelance.android.EarthquakeApp.models.Features;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Kyaw Khine on 10/10/2017.
 */

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.MyViewHolder> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getName();

    private Context context;
    private List<Features> featuresList;
    final private ListItemClickListener mOnClickListener;

    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Context mContext, List<Features> featuresList, ListItemClickListener listener) {
        Log.i(LOG_TAG, "TEST: EarthquakeAdapter() constructor called...");

        this.context = mContext;
        this.featuresList = featuresList;
        mOnClickListener = listener;
    }

    @Override
    public EarthquakeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(LOG_TAG, "TEST: onCreateViewHolder() called...");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_city_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EarthquakeAdapter.MyViewHolder holder, int position) {
        Log.i(LOG_TAG, "TEST: onBindViewHolder()  called...");

        String magnitude = Double.toString(featuresList.get(position).getProperties().getMag());
        holder.mag.setText(magnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) holder.mag.getBackground();
        int magnitudeColor = getMagnitudeColor(featuresList.get(position).getProperties().getMag());
        magnitudeCircle.setColor(magnitudeColor);

        String originalLocation = featuresList.get(position).getProperties().getPlace();

        String locationOffset, primaryLocation;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {

            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;

            primaryLocation = parts[1];
        } else {
            // Otherwise, there is no " of " text in the originalLocation string.
            // Hence, set the default location offset to say "Near the".
            locationOffset = context.getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        holder.locationOffset.setText(locationOffset);
        holder.primaryLocation.setText(primaryLocation);

        Date dateTimeObject = new Date(featuresList.get(position).getProperties().getTime());

        String formattedDate = formatDate(dateTimeObject);
        holder.date.setText(formattedDate);

        String formattedTime = formatTime(dateTimeObject);
        holder.time.setText(formattedTime);
    }

    @Override
    public int getItemCount() {
        Log.i(LOG_TAG, "TEST: getItemCount()  called...");

        return featuresList.size();
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        Log.i(LOG_TAG, "TEST: formatDate()  called...");

        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        Log.i(LOG_TAG, "TEST: formatTime()  called...");

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        Log.i(LOG_TAG, "TEST: getMagnitudeColor()  called...");

        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(context, magnitudeColorResourceId);
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickItemIndex);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final String LOG_TAG = MyViewHolder.class.getName();

        private TextView mag, locationOffset, primaryLocation, date, time;

        public MyViewHolder(View itemView) {
            super(itemView);

            mag = (TextView) itemView.findViewById(R.id.tvMagnitude);
            locationOffset = (TextView) itemView.findViewById(R.id.tvLocationOffset);
            primaryLocation = (TextView) itemView.findViewById(R.id.tvPrimaryLocation);
            date = (TextView) itemView.findViewById(R.id.tvDate);
            time = (TextView) itemView.findViewById(R.id.tvTime);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i(LOG_TAG, "TEST: onClick() called...");
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
