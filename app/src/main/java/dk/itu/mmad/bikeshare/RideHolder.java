package dk.itu.mmad.bikeshare;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class RideHolder extends RecyclerView.ViewHolder {

//    private TextView mBikeNameView;
    private TextView mOriginView;
    private TextView mDestinationView;
    private TextView mBikeIDView;
    private TextView mStartLocationView;
    private TextView mEndLocationView;
    private TextView mCostView;

    public RideHolder(LayoutInflater inflater, ViewGroup parent){
        super(inflater.inflate(R.layout.list_item_ride,parent,false));
//        mBikeNameView = itemView.findViewById(R.id.what_bike_ride);
        mOriginView = itemView.findViewById(R.id.start_ride);
        mDestinationView = itemView.findViewById(R.id.end_ride);
        mBikeIDView = itemView.findViewById(R.id.bike_id);
        mStartLocationView = itemView.findViewById(R.id.start_location);
        mEndLocationView = itemView.findViewById(R.id.end_location);
        mCostView = itemView.findViewById(R.id.ride_cost);
    }
    public void bind(Ride ride) {
//        mBikeNameView.setText(ride.getBikeName());
        mBikeIDView.setText("BIKE ID: " + String.valueOf(ride.getBike_id()));

        mOriginView.setText("ORIGIN: "  +ride.getStartRide());


        String locationStr = "Latitude: " + String.valueOf(ride.getMLatitude()) + " and Longitude: "  + String.valueOf(ride.getMLongitude());
        mStartLocationView.setText(locationStr);

        mDestinationView.setText("DESTINATION: " +ride.getEndRide());
        if(!ride.getEndRide().equals("")){
            String endLocationStr = "Latitude: " + String.valueOf(ride.getMEndLatitude()) + " and Longitude: "  + String.valueOf(ride.getMEndLongitude());
            mEndLocationView.setText(endLocationStr);

        }
        mCostView.setText("Cost: " + String.valueOf(ride.getCost()));


    }

}
