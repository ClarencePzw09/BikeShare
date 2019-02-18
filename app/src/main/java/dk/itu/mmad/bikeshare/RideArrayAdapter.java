package dk.itu.mmad.bikeshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RideArrayAdapter extends ArrayAdapter<Ride> {

    private final Context mContext;
    private final List<Ride> mValues;

    public RideArrayAdapter(Context context, List<Ride> values){
        super(context,R.layout.list_item_ride,values);
        mContext = context;
        mValues = values;
    }

    @Override
    public View getView(int position, View concertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_ride,parent
        ,false);
        TextView bikeView = (TextView) rowView.findViewById(R.id.what_bike_ride);
        bikeView.append(mValues.get(position).getmBikeName());
        TextView originView = (TextView) rowView.findViewById(R.id.start_ride);
        originView.append(mValues.get(position).getmStartRide());
        TextView destinationView = (TextView) rowView.findViewById(R.id.end_ride);
        destinationView.append(mValues.get(position).getmEndRide());

        return rowView;
    }

}
